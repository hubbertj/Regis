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
            $('#registration-form').on("submit", jQuery.proxy(this, "onSubmit"));

            $('input[type=radio][name="afternoonRadio"] ').click(function() {
                if (this.previous) {
                    this.checked = false;
                }
                this.previous = this.checked;
            });

            $('.popup-overlay .cls').on('click', jQuery.proxy(this, 'onCloseRegistrationFailedModal'));
            $('form#registration-form :input').change(jQuery.proxy(this, 'onChange'));
        }

        /**
         * Handles all field change events.
         * @param  {[type]} event [description]
         * @return {[type]}       [description]
         */
        onChange(evt) {
            let name = '';
            if ('currentTarget' in evt) {
                name = $(evt.currentTarget).attr('name');
                console.log(`${name} has triggered a event`);
            };
            switch (name) {
                case 'x':
                    break;
                case 'y':
                    break;
                default:
            }
            return false;
        }

        /**
         * Closes our failed modal
         * @param  {[type]} event [description]
         * @return {[type]}       [description]
         */
        onCloseRegistrationFailedModal(event) {
            $('.popup-overlay, .popup-content').removeClass("active");
            $('.popup-overlay .message').text('');
            return false;
        }

        /**
         * Opens our failed modal
         * @param  {[type]} error [description]
         * @return {[type]}       [description]
         */
        showRegistrationFailedModal(error) {
            $('.popup-overlay .message').text(error.message);
            const el = $(".popup-overlay, .popup-content");
            el.addClass("active");
            $([document.documentElement, document.body]).animate({
                scrollTop: el.offset().top
            }, 1000);
        }

        /**
         * Handles the submittion
         * @return {[type]} [description]
         */
        onSubmit(e) {
            let data = {};
            $($(e.currentTarget).serializeArray()).each(function(index, obj) {
                data[obj.name] = obj.value;
            });
            const { morningRadio, afternoonRadio, eveningRadio, mealPackRadio, mealPackDay2Radio } = data;
            const error = RegistrationController.validateWorkshops({
                morningRadio,
                afternoonRadio,
                eveningRadio,
                mealPackRadio,
                mealPackDay2Radio,
            });

            if (error) {
                //TODO: this may need to be a modal rather then a alert
                this.showRegistrationFailedModal(error);
                // conference.alert(error.message, 'danger');
                return false;
            }

            window.location.href = 'thankyou.html';
            return false;
        }

        /**
         * Private for checking if workshop selection is valid.
         * @param  {[type]} selections 
         * @return {[type]}
         */
        static validateWorkshops(selections) {
            let error = false;
            if (selections.morningRadio === 'discovery' && selections.afternoonRadio) {
                error = { message: 'Discovery includes all afternoon workshops: pitching, power company simulation, and exit strategy.' }
            } else if (selections.afternoonRadio && selections.afternoonRadio === 'exitStrateg' && selections.eveningRadio !== 'sales') {
                error = { message: 'Attendees who select Exit Strategy for their afternoon workshops must also choose Sales for the Evening workshops.' }
            } else if (selections.eveningRadio === 'sales' && selections.afternoonRadio !== 'exitStrateg') {
                error = { message: 'Attendees who select Evening session workshop Sales must also take afternoon session workshop Exit Strategy.' }
            } else if (selections.morningRadio === 'discovery' && selections.eveningRadio === 'sales') {
                error = { message: 'Discovery includes all Evening workshops: Sales.' }
            }
            return error;
        }
    }

    conference.controller = new RegistrationController();

})(window.conference);