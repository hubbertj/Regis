# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Server file for management of flask server

from flask import Flask, render_template, send_from_directory
from dotenv import load_dotenv
from os import environ


def create_app():
    app = Flask(__name__)
    return app


def add_routes(app):

    @app.route('/')
    def index():
        return render_template('index.html')

    @app.route('/activates')
    def activities():
        return render_template('activates.html')

    @app.route('/awards')
    def awards():
        return render_template('awards.html')

    @app.route('/keynote')
    def keynote():
        return render_template('keynote.html')

    @app.route('/meals')
    def meals():
        return render_template('meals.html')

    @app.route('/poll')
    def poll():
        return render_template('poll.html')

    @app.route('/registration')
    def registration():
        return render_template('registration.html')

    @app.route('/thankyou')
    def thank_you():
        return render_template('thankyou.html')

    @app.route('/workshopschedule')
    def workshop_schedule():
        return render_template('workshopschedule.html')

    @app.route('/nametags8gen')
    def name_tags_8():
        return render_template('nametags/nametags8gen.html')

    @app.route('/nametags10gen')
    def name_tags_10():
        return render_template('nametags/nametags10gen.html')

    @app.route('/admin')
    def admin():
        return render_template('admin.html')

    @app.route('/test')
    def test():
        animal_list = ['dog', 'cat', 'mouse']
        return render_template('test.html', animallist=animal_list)

    return app


def add_static(app):
    @app.route('/js/<path:path>')
    def send_js(path):
        return send_from_directory('js', path)

    @app.route('/images/<path:path>')
    def send_images(path):
        return send_from_directory('images', path)

    @app.route('/css/<path:path>')
    def send_css(path):
        return send_from_directory('css', path)

    @app.route('/fonts/<path:path>')
    def send_fonts(path):
        return send_from_directory('fonts', path)

    return app


load_dotenv('.env')

application = create_app()
application = add_routes(application)
application = add_static(application)

if environ.get('FLASK_ENV'):
    print('Running in ' + environ.get('FLASK_ENV') + ' environment')

if __name__ == '__main__':
    application.run(port=environ.get('PORT'))
