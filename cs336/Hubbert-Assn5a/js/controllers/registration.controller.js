(function(conference) {

    if (!conference) {
        return false;
    }

    class RegistrationController {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`RegistrationController has been init with ${JSON.stringify(data)}`);
            $('#registration-form').on("submit", this.onSubmit);
        }

        /**
         * Handles the submittion
         * @return {[type]} [description]
         */
        onSubmit(e) {
            let data = {};
            $($(this).serializeArray()).each(function(index, obj) {
                data[obj.name] = obj.value;
            });
            console.log(data);
            return false;
        }
    }

    conference.controller = new RegistrationController();

})(window.conference);