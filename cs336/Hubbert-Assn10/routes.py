# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Routes file for management of flask server

from flask import request, abort, jsonify, render_template, Blueprint, send_from_directory
from flask_login import current_user, login_required
from models.registrationtable import Registrant, db
from models.userstable import User
import datetime

route_all = Blueprint('route_all', __name__)
route_static = Blueprint('route_static', __name__)
api_crud = Blueprint('api_crud', __name__)


@route_all.route('/')
def index():
    return render_template('index.html')


@route_all.route('/activities')
def activities():
    return render_template('activities.html')


@route_all.route('/awards')
def awards():
    return render_template('awards.html')


@route_all.route('/keynote')
def keynote():
    return render_template('keynote.html')


@route_all.route('/meals')
def meals():
    return render_template('meals.html')


@route_all.route('/poll', methods=['GET', 'POST', 'PUT', 'DELETE'])
def poll():
    if request.method == 'GET':
        return render_template('poll.html')
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


@route_all.route('/registration', methods=['GET', 'POST', 'PUT', 'DELETE'])
def registration():
    if request.method == 'GET':
        return render_template('registration.html')
    elif request.method == 'POST':
        meal_pack = None
        if request.form.get('mealPackDay2Radio') == 'yes':
            meal_pack = 'dinnerday2'
        elif request.form.get('mealPackRadio') == 'yes':
            meal_pack = 'mealpack'
        new_registrant = Registrant(
            date=datetime.datetime.now(),
            title=request.form.get('title'),
            firstname=request.form.get('firstName'),
            lastname=request.form.get('lastName'),
            address1=request.form.get('addressLine1'),
            address2=request.form.get('addressLine2'),
            city=request.form.get('city'),
            state=request.form.get('state'),
            zipcode=request.form.get('zipCode'),
            phone=request.form.get('phone'),
            email=request.form.get('emailAddress'),
            web=request.form.get('companyWebsite'),
            job_title=request.form.get('position'),
            company=request.form.get('companyName'),
            meal_pack=meal_pack,
            billing_firstname=request.form.get('billFirstName'),
            billing_lastname=request.form.get('billLastName'),
            card_type=request.form.get('cardRadio'),
            card_number=request.form.get('cardNumber'),
            sid=request.form.get('cvs'),
            exp_year=request.form.get('expirationYear'),
            exp_month=request.form.get('expirationMonth'),
            session1=request.form.get('morningRadio'),
            session2=request.form.get('afternoonRadio'),
            session3=request.form.get('eveningRadio'),
        )
        db.session.add(new_registrant)
        db.session.commit()
        return jsonify(process=True, message='registration successful', registrant=new_registrant.serialized)

    elif request.method == 'PUT':
        return jsonify(process=True, message='')
    elif request.method == 'DELETE':
        return jsonify(process=True, message='')
    else:
        abort(401)


@route_all.route('/thankyou')
def thank_you():
    return render_template('thankyou.html')


@route_all.route('/workshopschedule')
def workshop_schedule():
    return render_template('workshopschedule.html')


@route_all.route('/nametags/nametags8gen')
@login_required
def name_tags_8():
    return render_template('nametags/nametags8gen.html')


@route_all.route('/nametags/nametags10gen')
@login_required
def name_tags_10():
    return render_template('nametags/nametags10gen.html')


@route_all.route('/admin')
@login_required
def admin():
    c_user = {
        'user_id': current_user.id,
        'username': current_user.username,
    }
    return render_template('admin.html', user=c_user)


# CRUD METHODS
@api_crud.route('/registrant/<registrant_id>', methods=['GET', 'POST', 'PUT', 'DELETE'])
def registrant(registrant_id):
    if request.method == 'GET':
        f_registrant = Registrant.get(registrant_id)
        if f_registrant is not None:
            return jsonify(process=True, registrant=f_registrant.serialized)
        else:
            return jsonify(process=True, message=registrant_id + ' not found')
    else:
        abort(401)


@api_crud.route('/user/<user_id>', methods=['GET', 'POST', 'PUT', 'DELETE'])
def user(user_id):
    if request.method == 'GET':
        f_user = User.get(user_id)
        if f_user is not None:
            return jsonify(process=True, registrant=f_user.serialized)
        else:
            return jsonify(process=True, message=user_id + ' not found')
    else:
        abort(401)


# STATIC ROUTES
@route_static.route('/js/<path:path>')
def send_js(path):
    return send_from_directory('js', path)


@route_static.route('/images/<path:path>')
def send_images(path):
    return send_from_directory('images', path)


@route_static.route('/css/<path:path>')
def send_css(path):
    return send_from_directory('css', path)


@route_static.route('/fonts/<path:path>')
def send_fonts(path):
    return send_from_directory('fonts', path)


@route_static.route('/static/<path:path>')
def send_static(path):
    return send_from_directory('static', path)
