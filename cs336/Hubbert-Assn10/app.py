# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Server file for management of flask server

from flask import Flask, render_template
from dotenv import load_dotenv
from os import environ
from routes import route_all, route_static
from models import registrationtable, userstable, workshoptable, awardtable


def seed_db():
    registrationtable.Registrant().seed()
    userstable.User().seed()
    workshoptable.Workshop().seed()
    awardtable.Nominee().seed()


def create_app():
    app = Flask(__name__)

    if environ.get('SQLALCHEMY_DATABASE_URI'):
        app.config['SQLALCHEMY_DATABASE_URI'] = environ.get('SQLALCHEMY_DATABASE_URI')

    if environ.get('SQLALCHEMY_TRACK_MODIFICATIONS'):
        app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = environ.get('SQLALCHEMY_TRACK_MODIFICATIONS')

    registrationtable.db.init_app(app)
    userstable.db.init_app(app)
    workshoptable.db.init_app(app)
    awardtable.db.init_app(app)

    app.register_blueprint(route_all)
    app.register_blueprint(route_static)
    return app


def add_error_handlers(app):
    @app.errorhandler(404)
    def page_not_found(error):
        return render_template('404.html')

    @app.errorhandler(401)
    def unauthorized_request(error):
        return 'User cannot perform this action', 401

    return app


load_dotenv('.env')
application = create_app()
application = add_error_handlers(application)

with application.app_context():
    registrationtable.db.create_all()
    userstable.db.create_all()
    workshoptable.db.create_all()
    awardtable.db.create_all()
    if environ.get('SEED') == '1':
        seed_db()


if environ.get('FLASK_ENV'):
    print(' * Running in ' + environ.get('FLASK_ENV') + ' environment')

if __name__ == '__main__':
    application.run(port=environ.get('PORT'))
