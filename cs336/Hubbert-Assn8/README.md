# Hubbert-Assn8

We will now move the site into Flask. In this assignment, we will shift the site’s HTML
pages into Flask and convert them to templates. Additionally, we will slightly reorganize
the site so that images, javascript, and css reside in the static directory.

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install pipenv.
```bash
pip install pipenv
```
Once installed you need to connect pipenv to your PyCharm by adding a new
python interpreter.

Use the following instructions to do so [install pipenv into Pycharm](https://www.jetbrains.com/help/pycharm/pipenv.html)

### Generate JS Libs
navigate to /static
install npm [install npm](https://www.npmjs.com/get-npm)
run the following in your /static DIR
```bash
npm install 
```
This should generate a /static/node_module DIR which holds all JS Libs for the site.

## Usage
### How to run server
1. Create run configuration for app.py.
2. Create a .env file in the root.
3. Copy a one of the environment variable sets from .env.template into .env (development | production)
4. Run script app.py using run configuration.

### How to generate name tags
1. Create run configuration for scripts/name_tags.py using pipenv
2. Run script name_tags.py using run configuration
3. Generated HTML file should be places at DIR templates/nametags/


## Contributing
Jerum (Jay) Hubbert

## License
[MIT](https://choosealicense.com/licenses/mit/)