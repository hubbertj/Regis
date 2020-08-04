# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Server file for management of flask server

from flask import Flask, request, abort, jsonify, render_template, send_from_directory
from dotenv import load_dotenv
from os import environ


def create_app():
    app = Flask(__name__)

    @app.errorhandler(404)
    def page_not_found(error):
        return render_template('404.html')

    @app.errorhandler(401)
    def unauthorized_request(error):
        return 'User cannot perform this action', 401

    return app


def add_routes(app):
    @app.route('/')
    def index():
        return render_template('index.html')

    @app.route('/activities')
    def activities():
        return render_template('activities.html')

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

    @app.route('/registration', methods=['GET', 'POST', 'PUT', 'DELETE'])
    def registration():
        if request.method == 'GET':
            return render_template('registration.html')
        elif request.method == 'POST':
            print(request.form)
            return jsonify(process=True, message='')
        elif request.method == 'PUT':
            print(request.form)
            return jsonify(process=True, message='')
        elif request.method == 'DELETE':
            print(request.form)
            return jsonify(process=True, message='')
        else:
            abort(401)

    @app.route('/thankyou')
    def thank_you():
        return render_template('thankyou.html')

    @app.route('/workshopschedule')
    def workshop_schedule():
        return render_template('workshopschedule.html')

    @app.route('/nametags/nametags8gen')
    def name_tags_8():
        return render_template('nametags/nametags8gen.html')

    @app.route('/nametags/nametags10gen')
    def name_tags_10():
        return render_template('nametags/nametags10gen.html')

    @app.route('/admin')
    def admin():
        return render_template('admin.html')

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

    @app.route('/static/<path:path>')
    def send_static(path):
        return send_from_directory('static', path)

    return app


load_dotenv('.env')

application = create_app()
application = add_routes(application)
application = add_static(application)

if environ.get('FLASK_ENV'):
    print('Running in ' + environ.get('FLASK_ENV') + ' environment')

if __name__ == '__main__':
    application.run(port=environ.get('PORT'))
