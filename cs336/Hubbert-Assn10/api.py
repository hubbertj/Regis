from flask import request, abort, jsonify, render_template, Blueprint
from flask_login import login_required
from sqlalchemy import or_
from models.registrationtable import Registrant, db as registrant_db
from models.userstable import User
from models.awardtable import Nominee, db as nominee_db
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


@api_crud.route('/nominee', methods=['GET'])
def nominees():
    all_nominees = Nominee.query.all()
    return jsonify(process=True, nominees=[n.serialized for n in all_nominees])


@api_crud.route('/nominee/<nominee_id>', methods=['GET', 'PUT'])
def nominee(nominee_id):
    if request.method == 'GET':
        f_nominee = Nominee.query.filter_by(id=nominee_id).first()
        if f_nominee is not None:
            return jsonify(process=True, message='Nominee successfully found', nominee=f_nominee.serialized)
        else:
            return jsonify(process=True, message='failed to find nominee ' + nominee_id), 404
    elif request.method == 'PUT':
        votes = int(request.form.get('votes'))
        f_nominee = Nominee.query.filter_by(id=nominee_id).first()
        if f_nominee is not None and votes is not None:
            try:
                f_nominee.vote_count = f_nominee.vote_count + votes
                nominee_db.session.add(f_nominee)
                nominee_db.session.commit()
            except Exception as e:
                print(e)
                return jsonify(message='Nominee vote update failed', error=str(e)), 500

        else:
            return jsonify(process=True, message='failed to update votes'), 401
        all_nominees = Nominee.query.all()
        return jsonify(process=True, nominees=[n.serialized for n in all_nominees])
    else:
        return jsonify(process=True, message='Not Implemented'), 404


@api_crud.route('/registrant/search', methods=['GET'])
@login_required
def registrant_search():
    if request.method == 'GET':
        workshop_list = request.args.getlist('workshops[]')
        meal_pack = request.args.get('meal_pack')

        try:
            if request.args is None or len(request.args) == 0:
                registrants = Registrant.query.all()
                return jsonify(process=True, title='List of all registrants',
                               registrants=[reg.serialized for reg in registrants])
            if workshop_list is not None and meal_pack is not None:
                query = registrant_db.session.query(Registrant).filter(or_(Registrant.meal_pack == meal_pack))
                query.filter(or_(Registrant.session1.in_(workshop_list)))
                query.filter(or_(Registrant.session2.in_(workshop_list)))
                query.filter(or_(Registrant.session3.in_(workshop_list)))

                registrants = query.all()
                return jsonify(process=True,
                               title='People taking ' + ', '.join(
                                   map(str, workshop_list)) + ' & purchased meal option ' + str(
                                   meal_pack),
                               registrants=[reg.serialized for reg in registrants])
            elif workshop_list is not None and meal_pack is None:
                query = registrant_db.session.query(Registrant).filter(or_(Registrant.session1.in_(workshop_list)))
                query.filter(or_(Registrant.session2.in_(workshop_list)))
                query.filter(or_(Registrant.session3.in_(workshop_list)))

                registrants = query.all()
                return jsonify(process=True,
                               title='People taking ' + ', '.join(map(str, workshop_list)),
                               registrants=[reg.serialized for reg in registrants])
            else:
                registrants = Registrant.query.filter_by(meal_pack=meal_pack)
                return jsonify(process=True, title='People who purchased meal options ' + str(meal_pack),
                               registrants=[reg.serialized for reg in registrants])
        except Exception as e:
            print(e)
            return jsonify(message='Registrant search results failed', error=str(e)), 500
    else:
        return jsonify(process=True, message='Not Implemented'), 401


@api_crud.route('/user/<user_id>', methods=['GET', 'POST', 'PUT', 'DELETE'])
def user(user_id):
    if request.method == 'GET':
        f_user = User.query.get(user_id).first()
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
                zipcode=request.form.get('zipCode'),
                phone=request.form.get('phone'),
                email=request.form.get('emailAddress').lower(),
                web=request.form.get('companyWebsite').lower(),
                job_title=request.form.get('position').lower(),
                company=request.form.get('companyName').lower(),
                meal_pack=meal_pack,
                billing_firstname=request.form.get('billFirstName').lower(),
                billing_lastname=request.form.get('billLastName').lower(),
                card_type=request.form.get('cardRadio').lower(),
                card_number=request.form.get('cardNumber'),
                sid=request.form.get('cvs'),
                exp_year=request.form.get('expirationYear'),
                exp_month=request.form.get('expirationMonth'),
                session1=request.form.get('morningRadio'),
                session2=request.form.get('afternoonRadio'),
                session3=request.form.get('eveningRadio'),
                confirmation=str(uuid.uuid4()),
            )
            registrant_db.session.add(new_registrant)
            registrant_db.session.commit()
            return jsonify(process=True, message='registration successful', registrant=new_registrant.serialized)
        except Exception as e:
            if 'UNIQUE constraint failed: registrants.email' in str(e):
                response = jsonify(message='The email address has already registered for this conference', error=str(e))
            else:
                response = jsonify(message='System failed to registration patron', error=str(e))
            print(e)
            return response, 500
    elif request.method == 'DELETE':
        return jsonify(process=True, message='Not Implemented'), 401
    else:
        abort(401)
