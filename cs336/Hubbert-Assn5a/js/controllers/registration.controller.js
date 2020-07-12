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
            const error = RegistrationController.validateWorkshops(data);

            if(error){
                alert(error.message);
                return false;
            }
            console.log(data);
            window.location.href = 'thankyou.html';
            return false;
        }

        static validateWorkshops(data){
            return false;
        }
    }

    conference.controller = new RegistrationController();

})(window.conference);