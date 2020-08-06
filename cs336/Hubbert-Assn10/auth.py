from flask import Blueprint, render_template, request, abort, url_for, redirect
from flask_login import login_user, login_required, logout_user

auth = Blueprint('auth', __name__)


@auth.route('/login', methods=['POST', 'GET'])
def login():
    if request.method == 'GET':
        return render_template('login.html')
    elif request.method == 'POST':
        # login_user(user, remember=remember)
        return redirect(url_for('route_all.admin'))
        pass
    else:
        abort(401)


@auth.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('route_all.index'))
