package rest.service;

import beans.dao.interfaces.MealLocal;
import beans.dao.interfaces.RestaurantLocal;
import beans.dao.interfaces.UserLocal;
import javafx.scene.control.Tab;
import model.dao.Meal;
import model.dao.Restaurant;
import model.dao.Table;
import model.dao.User;
import rest.model.restaurant.*;
import rest.model.user.CreateManagerRequest;
import rest.model.user.UserResponse;
import rest.util.BasicResponse;
import rest.util.ResponseExceptions;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - Srđan Milaković
 */
@Path("/restaurant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantRest {


    @EJB
    private UserLocal userBean;
    @EJB
    private MealLocal mealBean;
    @EJB
    private RestaurantLocal restaurantBean;


    //<editor-fold desc="Restaurant CRU">
    @GET
    @RolesAllowed({User.CUSTOMER, User.ADMINISTRATOR, User.MANAGER})
    public Object getAll(@Context User user) {
        switch (user.getRole()) {
            case User.CUSTOMER:
                return getAllRestaurantsCustomer(user.getId());
            case User.MANAGER:
                return getAllRestaurantsManager(user.getRestaurantId().getId());
            case User.ADMINISTRATOR:
                return getAllRestaurantsAdmin();
        }
        return getAllRestaurantsAdmin();
    }

    @GET
    @Path("{id}")
    @RolesAllowed({User.CUSTOMER, User.ADMINISTRATOR, User.MANAGER})
    public Object get(@PathParam("id") Integer id) {
        Restaurant restaurant = restaurantBean.find(id);
        if (restaurant == null) {
            throw ResponseExceptions.createNotFound();
        }

        return new RestaurantResponse(restaurant);
    }

    @POST
    @RolesAllowed(User.ADMINISTRATOR)
    public Object create(@Valid RestaurantRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());
        restaurantBean.create(restaurant);

        return BasicResponse.createCreated();
    }

    @PUT
    @Path("{id}")
    @RolesAllowed({User.ADMINISTRATOR, User.MANAGER})
    public Object edit(@PathParam("id") Integer id, @Valid RestaurantRequest request, @Context User user) {
        Restaurant restaurant = restaurantBean.find(id);
        if (restaurant == null) {
            throw ResponseExceptions.createNotFound();
        } else if (!restaurant.equals(user.getRestaurantId())) {
            throw ResponseExceptions.createForbidden();
        }

        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());
        restaurantBean.edit(restaurant);

        return BasicResponse.createCreated("Restaurant has been changed.");
    }

    //<editor-fold desc="getAllRestaurantsRole">
    private Object getAllRestaurantsCustomer(int userId) {
        // TODO: Implement
        return getAllRestaurantsAdmin();
    }

    private Object getAllRestaurantsManager(int restaurantId) {
        List<RestaurantResponse> response = new ArrayList<>();
        restaurantBean.findAll().forEach(restaurant -> {
            if (restaurant.getId().equals(restaurantId)) {
                response.add(new RestaurantResponse(restaurant, true));
            } else {
                response.add(new RestaurantResponse(restaurant, false));
            }

        });
        return response;
    }

    private Object getAllRestaurantsAdmin() {
        List<RestaurantResponse> response = new ArrayList<>();
        restaurantBean.findAll().forEach(restaurant -> response.add(new RestaurantResponse(restaurant)));
        return response;
    }
    //</editor-fold>

    //</editor-fold>

    //<editor-fold desc="Managers CR">
    @GET
    @Path("/{id}/manager")
    @RolesAllowed(User.ADMINISTRATOR)
    public Object getManagers(@PathParam("id") Integer id) {
        Restaurant restaurant = restaurantBean.find(id);
        if (restaurant == null) {
            throw ResponseExceptions.createNotFound();
        }

        List<UserResponse> response = new ArrayList<>();
        restaurant.getUserSet().forEach(user -> response.add(new UserResponse(user)));

        return response;
    }

    @POST
    @Path("/{id}/manager")
    @RolesAllowed(User.ADMINISTRATOR)
    public Object createManager(@PathParam("id") Integer id, @Valid CreateManagerRequest managerRequest) {
        managerRequest.setRestaurantId(id);

        Restaurant restaurant = restaurantBean.find(id);
        if (restaurant == null) {
            throw ResponseExceptions.createNotFound();
        }

        if (userBean.findByEmail(managerRequest.getEmail()) != null) {
            throw ResponseExceptions.createBadRequest("Email is already used.");
        }
        userBean.create(managerRequest);

        return BasicResponse.createCreated("Manager created.");
    }
    //</editor-fold>

    //<editor-fold desc="Meals CRUD">
    @GET
    @Path("/{id}/meal")
    @RolesAllowed({User.CUSTOMER, User.MANAGER, User.ADMINISTRATOR})
    public Object getAllMeals(@PathParam("id") Integer id, @Context User user) {
        List<MealResponse> responses = new ArrayList<>();
        restaurantBean.find(id).getMealSet().forEach(meal -> responses.add(new MealResponse(meal)));

        return responses;
    }

    @GET
    @Path("/meal/{id}")
    @RolesAllowed({User.CUSTOMER, User.MANAGER, User.ADMINISTRATOR})
    public Object getMeal(@PathParam("id") Integer id, @Context User user) {
        return new MealResponse(getMealValidate(id, user));
    }

    @POST
    @Path("/{id}/meal")
    @RolesAllowed(User.MANAGER)
    public Object createMeal(@PathParam("id") Integer id, @Context User user, @Valid MealRequest mealRequest) {
        Restaurant restaurant = restaurantBean.find(id);
        if (restaurant == null) {
            return ResponseExceptions.createNotFound();
        }

        Meal meal = new Meal();
        meal.setName(mealRequest.getName());
        meal.setDescription(mealRequest.getDescription());
        meal.setPrice(mealRequest.getPrice());
        meal.setRestaurantId(restaurant);

        mealBean.create(meal);

        return BasicResponse.createCreated();
    }

    @PUT
    @Path("/meal/{id}")
    @RolesAllowed(User.MANAGER)
    public Object editMeal(@PathParam("id") Integer id, @Context User user, @Valid MealRequest mealRequest) {
        Meal meal = getMealValidate(id, user);
        meal.setName(mealRequest.getName());
        meal.setDescription(mealRequest.getDescription());
        meal.setPrice(mealRequest.getPrice());
        mealBean.edit(meal);

        return BasicResponse.createChanged();
    }

    @DELETE
    @Path("/meal/{id}")
    @RolesAllowed(User.MANAGER)
    public Object deleteMeal(@PathParam("id") Integer id, @Context User user) {
        mealBean.remove(getMealValidate(id, user));
        return BasicResponse.createDeleted();
    }

    private Meal getMealValidate(Integer mealId, User user) {
        Meal meal = mealBean.find(mealId);
        if (meal == null) {
            throw ResponseExceptions.createNotFound();
        } else if (!meal.getRestaurantId().equals(user.getRestaurantId())) {
            throw ResponseExceptions.createForbidden();
        }

        return meal;
    }
    //</editor-fold>

    @GET
    @Path("/{id}/table")
    @RolesAllowed({User.CUSTOMER, User.MANAGER, User.ADMINISTRATOR})
    public Object getAllTables(@PathParam("id") Integer id) {
        Restaurant restaurant = restaurantBean.find(id);
        if (restaurant == null) {
            throw ResponseExceptions.createNotFound();
        }

        if (restaurant.getRows() == 0 || restaurant.getColumns() == 0) {
            return new TablesDto(0, 0);
        }

        List<Table> tables = restaurant.getTableList();
        TablesDto tablesDto = new TablesDto(restaurant.getRows(), restaurant.getColumns());
        for (Table table : tables) {
            tablesDto.setTable(table.getId(), table.getRow(), table.getColumn(), table.getLabel(), TablesDto.TABLE);
        }

        return tablesDto;
    }

    @POST
    @Path("/{id}/table")
    @RolesAllowed(User.MANAGER)
    public Object setTables(@PathParam("id") Integer id, @Context User user, TablesDto dto) {
        if (!id.equals(user.getRestaurantId().getId())) {
            throw ResponseExceptions.createUnauthorized();
        }

        Restaurant restaurant = restaurantBean.find(id);
        if (restaurant == null) {
            throw ResponseExceptions.createNotFound();
        }

        List<Table> tables = new ArrayList<>();
        for (List<TablesDto.TableDto> row : dto.getTables()) {
            for (TablesDto.TableDto table : row) {
                switch (table.getType()) {
                    case TablesDto.TABLE:
                        tables.add(new Table(null, table.getRow(), table.getColumn(), table.getLabel()));
                        break;
                    case TablesDto.NO_TABLE:
                        break;
                    default:
                        throw ResponseExceptions.createBadRequest("Invalid table type: " + table.getType());
                }
            }
        }

        restaurantBean.setTableConfiguration(restaurant, dto.getRows(), dto.getColumns(), tables);

        return BasicResponse.createChanged();
    }
}
