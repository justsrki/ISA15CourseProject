var authHttpRequestInterceptorModule = angular.module('app.authHttpRequestInterceptor', []);

authHttpRequestInterceptorModule.factory('authHttpRequestInterceptor', function ($rootScope, $injector) {
    "use strict";

    return {
        request: function ($request) {
            var tokenService = $injector.get('AccessToken');
            if (tokenService.hasCredentials()) {
                $request.headers['auth-token'] = tokenService.getAccessToken();
                $request.headers['user-id'] = tokenService.getUserId();
            }

            return $request;
        }
    };
});