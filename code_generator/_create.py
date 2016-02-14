import sys
import fileinput
import os


def to_snake_case(str) :
	snake_case = ''
	for c in str:
		if c.islower() or c.isdigit():
			snake_case += c
		elif c.isupper():
			snake_case += '_' + c.lower()

	return snake_case[1:] if snake_case.startswith('_') else snake_case


def create_component(name, type, path, ext, suffix):
	# copy template module and replace <name>
	full_path = path + to_snake_case(name) + '_' + type + ext
	template = open('_templates/_' + type + '_template' + ext, 'r')
	fout = open(full_path, 'w+')
	for line in template:
		fout.write(line.replace('<name>', name))
	template.close()
	fout.close()
	
	# modify /js/<module_type>.js
	name = name + suffix
	if ext == '.js':
		type_collection = 'app/' + type + 's.js'
		if os.path.isfile(type_collection):
			modules = open(type_collection, 'r')
			types_str = ''
			for line in modules:
				line = line.strip()
				if line.startswith('\'') or line.startswith('//') or line.startswith('/*'):
					types_str += '\t'
					
				if line.startswith('\'') and line.endswith('\''):
					types_str += line + ',\n'
					types_str += '\t' + '\'app.' + name + '\'\n'
				else:
					types_str += line + '\n'
			modules.close()
			
			modules = open('app/' + type + 's.js', 'w+')
			modules.write(types_str)
			modules.close()
		else:
			template = open('_templates/_' + type + 's_template' + ext, 'r')
			fout = open(type_collection, 'w+')

			for line in template:
				line = line.strip()
				fout.write(line.replace('<module>', '\t\'app.' + name + '\'') + '\n')
				
			template.close()
			fout.close()
		
		# modify index.html
		index = open('index.html', 'r')
		index_str = ''
		components = ['<!-- <modules> -->\n']
		for line in index:
			line = line.strip()
			if line.startswith('<script src="/app/components/'):
				components.append(line)
			else:
				index_str += line + '\n'
		index.close()
		
		components.append('<script src="' + full_path + '"></script>')
		components.sort();
		components_str = ''
		for comp in components:
			components_str += comp
		
		index = open('index.html', 'w+')
		index.write(index_str.replace('<!-- <modules> -->', components_str))
		index.close()

			
if len(sys.argv) < 3:
	print 'function must have at least 3 arguments'
	sys.exit()

name = sys.argv[2][0].upper() + sys.argv[2][1:] if sys.argv[2][0].islower() else sys.argv[2]
type = sys.argv[1]
path = 'app/components/' + (sys.argv[3] + '/' if len(sys.argv) == 4 else '')

if not os.path.exists(path):
	os.mkdir(path)

types = []
if 'c' in type: 	
	types.append(('controller', '.js', 'Ctrl'))
if 's' in type:
	types.append(('service', '.js', ''))
if 'v' in type:
	types.append(('view', '.html', ''))
	
for t in types:
	create_component(name, t[0], path, t[1], t[2])
	
	
