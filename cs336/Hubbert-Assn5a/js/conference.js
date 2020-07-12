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