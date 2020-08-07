(function(conference) {

    if (!conference) {
        return false;
    }

    class LoginController {
        constructor() {
            this.key = 'login-form';
            $('#login-form').on("submit", jQuery.proxy(this, "onSubmit"));
            $('input[type="checkbox"]').checkboxradio();
            this.loadUsername();
        }

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`LoginController has been init with ${JSON.stringify(data)}`);
        }

        /**
         * handles the submit event
         * @return {[boolean]}
         */
        onSubmit(e) {
            let data = {};
            $($(e.currentTarget).serializeArray()).each(function(index, obj) {
                data[obj.name] = obj.value;
            });

            new Promise((resolve, reject) => {
                $.ajax({
                    url: `/login`,
                    type: "POST",
                    data: data,
                    success: (response) => {
                        return resolve(response);
                    },
                    error: (err) => {
                        return resolve(err);
                    },
                });
            }).then((response) => {
                const { username, remember } = data;
                if(remember){
                    conference.setCookie(this.key, JSON.stringify({ username: username }), 365);
                } else {
                    conference.eraseCookie(this.key);
                }
                if (response) {
                    window.location.href = '/admin';
                }
            }).catch((err) => {
                if (err && 'responseJSON' in err && err.responseJSON && 'message' in err.responseJSON) {
                    conference.alert(err.responseJSON.message, 'danger');
                } else {
                    console.error(err);
                }
            });

            return false;
        }

        /**
         * Resets form
         * @return {null}
         */
        clearForm() {
            const form = $('#registration-form');
            form.find('input:not([type=checkbox]):not([type=radio]), textarea, select').val('');
            form.find('input[type=checkbox]').prop('checked', false).checkboxradio('refresh');
        }

        /**
         * On init of view loads cached username
         * @return {null}
         */
        loadUsername() {
            const savedCookie = conference.getCookie(this.key);
            if (savedCookie) {
                const data = JSON.parse(savedCookie);
                const { username } = data;
                if (username) {
                    const form = $('form#login-form');
                    this.clearForm();
                    form.find('input[name="remember"]').prop('checked', true).checkboxradio('refresh');
                    form.find('input[name="username"]').val(username);
                }
            }
        }
    }

    conference.controller = new LoginController();

})(window.conference);