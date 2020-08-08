from flask import request, abort, jsonify, render_template, Blueprint
from flask_login import login_required
from models.registrationtable import Registrant, db
from models.userstable import User
import datetime
import uuid

api_crud = Blueprint('api_crud', __name__)


# CRUD METHODS
@api_crud.route('/registrant/<confirmation>', methods=['GET'])
def registrant(confirmation):
    if request.method == 'GET':
        f_registrant = Registrant.query.filter_by(confirmation=confirmation).first()
        if f_registrant is not None:
            return jsonify(process=True, registrant=f_registrant.serialized)
        else:
            response = jsonify(process=True, message='confirmation=' + confirmation + ' not found')
            return response, 404
    else:
        abort(401)


@api_crud.route('/registrant/search', methods=['GET'])
@login_required
def registrant_search():
    if request.method == 'GET':
        print(request.args.get('workshops[]'))
        try:
            if request.args is None or len(request.args) == 0:
                registrants = Registrant.query.all()
                response = jsonify(process=True, registrants=[reg.serialized for reg in registrants])
                return response
        except Exception as e:
            return jsonify(message='registration failed', error=str(e)), 500

        return jsonify(process=True, message='Not Implemented'), 401
    else:
        abort(401)


@api_crud.route('/user/<user_id>', methods=['GET', 'POST', 'PUT', 'DELETE'])
def user(user_id):
    if request.method == 'GET':
        f_user = User.query.get(user_id)
        if f_user is not None:
            return jsonify(process=True, registrant=f_user.serialized)
        else:
            response = jsonify(process=True, message='id=' + user_id + ' not found')
            return response, 404
    else:
        abort(401)


@api_crud.route('/registration', methods=['GET', 'POST', 'PUT', 'DELETE'])
def registration():
    if request.method == 'GET':
        return render_template('registration.html')
    elif request.method == 'POST':
        meal_pack = None
        if request.form.get('mealPackDay2Radio') == 'yes':
            meal_pack = 'dinnerday2'
        elif request.form.get('mealPackRadio') == 'yes':
            meal_pack = 'mealpack'

        try:
            new_registrant = Registrant(
                date=datetime.datetime.now(),
                title=request.form.get('title').lower(),
                firstname=request.form.get('firstName').lower(),
                lastname=request.form.get('lastName').lower(),
                address1=request.form.get('addressLine1').lower(),
                address2=request.form.get('addressLine2').lower(),
                city=request.form.get('city').lower(),
                state=request.form.get('state').lower(),
                zipcode=request.form.get('zipCode').lower(),
                phone=request.form.get('phone').lower(),
                email=request.form.get('emailAddress').lower(),
                web=request.form.get('companyWebsite').lower(),
                job_title=request.form.get('position').lower(),
                company=request.form.get('companyName').lower(),
                meal_pack=meal_pack,
                billing_firstname=request.form.get('billFirstName').lower(),
                billing_lastname=request.form.get('billLastName').lower(),
                card_type=request.form.get('cardRadio').lower(),
                card_number=request.form.get('cardNumber').lower(),
                sid=request.form.get('cvs').lower(),
                exp_year=request.form.get('expirationYear').lower(),
                exp_month=request.form.get('expirationMonth').lower(),
                session1=request.form.get('morningRadio').lower(),
                session2=request.form.get('afternoonRadio').lower(),
                session3=request.form.get('eveningRadio').lower(),
                confirmation=str(uuid.uuid4()),
            )
            db.session.add(new_registrant)
            db.session.commit()
            return jsonify(process=True, message='registration successful', registrant=new_registrant.serialized)
        except Exception as e:
            response = jsonify(message='registration failed', error=str(e))
            return response, 500
    elif request.method == 'DELETE':
        return jsonify(process=True, message='Not Implemented'), 401
    else:
        abort(401)
