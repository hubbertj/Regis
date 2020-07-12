(function(window) {
    window.conference = {};

    /**
     * global object
     */
    class Conference {
        constructor() {
            $('.alert .close').on('click', this.onAlertClose);
        }

        /**
         * Closes a boostrap alert
         * @param  {[type]} event [description]
         * @return {[type]}       [description]
         */
        onAlertClose(event) {
            $('.alert').text('').attr('class', '').addClass('hide');
            return false;
        }
    }

    window.conference = new Conference();

})(window);