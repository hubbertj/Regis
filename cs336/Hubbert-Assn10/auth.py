from flask import Blueprint, render_template, request, abort, url_for, redirect, jsonify
from flask_login import login_user, login_required, logout_user, current_user
from models.userstable import User
from werkzeug.security import check_password_hash

auth = Blueprint('auth', __name__)


@auth.route('/login/check', methods=['GET'])
def login_check():
    if current_user.is_authenticated:
        return jsonify(process=True, status=True, user=current_user.serialized)
    else:
        return jsonify(process=True, status=False)


@auth.route('/login', methods=['POST', 'GET'])
def login():
    if current_user.is_authenticated:
        return redirect(url_for('route_all.admin'))
    if request.method == 'GET':
        return render_template('login.html')
    elif request.method == 'POST':
        username = request.form.get('username')
        password = request.form.get('password')
        user = User.query.filter_by(username=username).first()

        if not user or not check_password_hash(user.password, password):
            response = jsonify(message='username & password combination are incorrect')
            return response, 401

        login_user(user, remember=True)
        return jsonify(process=True, message='login successful',
                       user=current_user.serialized)
    else:
        abort(401)


@auth.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('route_all.index'))
