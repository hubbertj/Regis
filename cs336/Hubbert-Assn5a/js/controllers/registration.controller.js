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
            const radioValues = $(this).serializeArray();
            let radioData = {};
            $(radioValues).each(function(index, obj){
                radioData[obj.name] = obj.value;
            });

            var data = $(e.currentTarget).data();

            console.log(data);

            console.log(radioData);
            console.log(e);
            return false;
        }
    }

    conference.controller = new RegistrationController();

})(window.conference);