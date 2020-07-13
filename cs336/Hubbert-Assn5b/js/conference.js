(function(window) {


    /**
     * global object
     */
    window.conference = {
        /**
         * Closes a boostrap alert
         * @param  {[type]} event [description]
         * @return {[type]}       [description]
         */
        onAlertClose: (event) => {
            $('.alert span').text('');
            $('.alert').attr('class', '').addClass('hide alert');
            return false;
        },

        /**
         * Set Cookies 
         * @param  {[type]} name  [description]
         * @param  {[type]} value [description]
         * @param  {[type]} days  [description]
         * @return {[type]}       [description]
         */
        setCookie: (name, value, days) => {
            let expires = "";
            if (days) {
                let date = new Date();
                date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                expires = `expires=${date.toUTCString()}`;
            }
            document.cookie = `${name}=${value || ""}; ${expires}; path=/`;
            console.log(document.cookie);
            return document.cookie;
        },

        /**
         * Gets a cookies value
         * @param  {[type]} name [description]
         * @return {[type]}      [description]
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
         * @param  {[type]} name [description]
         * @return {[type]}      [description]
         */
        eraseCookie: (name) => {
            document.cookie = `${name}=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
            return false;
        },

        /**
         * Used to throw a alert
         * @param  {[type]} message [description]
         * @param  {[type]} type    [description]
         * @return {[type]}         [description]
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

    //init stuff
    $('.alert .close').on('click', conference.onAlertClose);


})(window);