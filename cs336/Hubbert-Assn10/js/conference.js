(function(window) {

    /**
     * global object
     */
    window.conference = {
        /**
         * Closes a bootstrap alert
         * @param  {object} event
         * @return {boolean}
         */
        onAlertClose: (event) => {
            $('.alert span').text('');
            $('.alert').attr('class', '').addClass('hide alert');
            return false;
        },


        /**
         * Actives the current link in nav element
         * @return {boolean}
         */
        loadNavLocation: () => {
            const currentRoute = $(location).attr('href').split('/').pop();
            $('#site-nav-links li').removeClass('active');
            const elem = $(`a[href*="${currentRoute}"]`);
            if (elem) {
                elem.parent().addClass('active');
            }
            return false
        },

        /**
         * Check if a user is logged in or not
         * @return {promise}
         */
        isLoggedIn: () => {
            return new Promise((resolve, reject) => {
                $.ajax({
                    url: `/login/check`,
                    type: "GET",
                    data: null,
                    success: (response) => {
                        return resolve(response);
                    },
                    error: (err) => {
                        return reject(err);
                    },
                });
            });
        },


        /**
         * Saves to local storage
         * @param  {string} name
         * @param  {string} obj
         * @return {boolean}
         */
        setLocalStorage: (name, obj) => {
            localStorage.setItem(name, JSON.stringify(obj));
            return false;
        },

        /**
         * Gets from local storage
         * @param  {string} name
         * @return {Object}
         */
        getLocalStorage: (name) => {
            return JSON.parse(localStorage.getItem(name)) || false;
        },

        /**
         * Set Cookies 
         * @param  {string} name
         * @param  {string} value
         * @param  {number} days
         * @return {Object}
         */
        setCookie: (name, value, days) => {
            let expires = "";
            if (days) {
                let date = new Date();
                date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                expires = `expires=${date.toUTCString()}`;
            }
            document.cookie = `${name}=${value || ""}; ${expires}; path=${window.location.pathname}; SameSite=Secure`;
            return document.cookie;
        },

        /**
         * Gets a cookies value
         * @param  {string} name
         * @return {boolean}
         */
        getCookie: (name) => {
            let nameEQ = `${name}=`;
            const ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') c = c.substring(1, c.length);
                if (c.indexOf(nameEQ) == 0) {
                    return c.substring(nameEQ.length, c.length);
                }
            }
            return false;
        },

        /**
         * Expires a cookie
         * @param  {string} name
         * @return {boolean}
         */
        eraseCookie: (name) => {
            document.cookie = `${name}=""; expires=Thu, 01 Jan 1970 00:00:01 GMT; Path=${window.location.pathname}; SameSite=none`;
            return false;
        },

        /**
         * Used to throw a alert
         * @param  {string} message
         * @param  {string} type
         * @return {boolean}
         */
        alert: (message, type) => {
            let addClass = 'info';
            if (type && type !== addClass) {
                switch (type) {
                    case 'success':
                        addClass = 'success';
                        break;
                    case 'warning':
                        addClass = 'warning';
                        break;
                    case 'danger':
                        addClass = 'danger';
                        break;
                    case 'light':
                        addClass = 'light';
                        break;
                    case 'dark':
                        addClass = 'dark';
                        break;
                    default:
                        addClass = 'info';
                }
            }
            addClass = `alert-${addClass}`;
            const alertElement = $('.alert');
            $('.alert span').text('');
            alertElement.attr('class', '').addClass('hide alert');;
            $('.alert span').text(message)
            alertElement.addClass(`show ${addClass}`);
            $([document.documentElement, document.body]).animate({
                scrollTop: alertElement.offset().top
            }, 1000);
            return false;
        },
    };

    // init stuff
    $('.alert .close').on('click', conference.onAlertClose);
    $.urlParam = function(name){
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        if (results==null) {
           return null;
        }
        return decodeURI(results[1]) || 0;
    }


})(window);