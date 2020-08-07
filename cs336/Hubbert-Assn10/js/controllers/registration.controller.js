(function(conference) {

    if (!conference) {
        return false;
    }

    class RegistrationController {

        constructor() {
            this.defaultConferenceId = 123456;
        }

        /**
         * Init the controller with any data from frontend
         * @return {null}
         */
        init(data) {
            console.log(`RegistrationController has been init with ${JSON.stringify(data)}`);

            $('#registration-form').on("submit", jQuery.proxy(this, "onSubmit"));

            $('input[type=radio][name="afternoonRadio"]').click(function() {
                if (this.previous) {
                    this.checked = false;
                }
                this.previous = this.checked;
                $(this).checkboxradio('refresh');
            });

            $('input[name="morningRadio"], input[name="afternoonRadio"], input[name="eveningRadio"], input[name="cardRadio"]')
                .checkboxradio();

            $('.popup-overlay .cls').on('click', jQuery.proxy(this, 'onCloseRegistrationFailedModal'));
            $('form#registration-form :input').change(jQuery.proxy(this, 'onChange'));
        }

        /**
         * Updates registration form from data model
         * @param  {Object} data
         * @return {boolean}
         */
        updateForm(data) {
            const form = $('form#registration-form');
            this.clearForm();

            form.find('input[name="addressLine1"]').val(data.addressLine1);
            form.find('input[name="addressLine2"]').val(data.addressLine2);
            form.find('input[name="city"]').val(data.city);
            form.find('input[name="companyName"]').val(data.companyName);
            form.find('input[name="companyWebsite"]').val(data.companyWebsite);
            form.find('input[name="conferenceId"]').val(data.conferenceId);
            form.find('input[name="emailAddress"]').val(data.emailAddress);
            form.find('input[name="firstName"]').val(data.firstName);
            form.find('input[name="lastName"]').val(data.lastName);
            form.find('input[name="phone"]').val(data.phone);
            form.find('input[name="position"]').val(data.position);
            form.find('input[name="zipCode"]').val(data.zipCode);
            form.find('select[name="state"]').val(data.state);
            form.find('select[name="title"]').val(data.title);
            
            form.find('input[name="billFirstName"]').val(data.billFirstName);
            form.find('input[name="billLastName"]').val(data.billLastName);
            form.find('input[name="cardNumber"]').val(data.cardNumber);
            form.find('input[name="cvs"]').val(data.cvs);
            form.find('input[name="expirationMonth"]').val(data.expirationMonth);
            form.find('input[name="expirationYear"]').val(data.expirationYear);

            if (data.morningRadio) {
                form.find(`input[type="radio"][value="${data.morningRadio}"]`).prop('checked', true).checkboxradio('refresh');
            }
            if (data.afternoonRadio) {
                form.find(`input[type="radio"][value="${data.afternoonRadio}"]`).prop('checked', true).checkboxradio('refresh');
            }
            if (data.eveningRadio) {
                form.find(`input[type="radio"][value="${data.eveningRadio}"]`).prop('checked', true).checkboxradio('refresh');
            }
            if (data.cardRadio) {
                form.find(`input[type="radio"][value="${data.cardRadio}"]`).prop('checked', true).checkboxradio('refresh');
            }
            if (data.mealPackRadio) {
                form.find(`input[type="radio"][value="${data.mealPackRadio}"]`).prop('checked', true).checkboxradio('refresh');
            }
            if (data.mealPackDay2Radio) {
                form.find(`input[type="radio"][value="${data.mealPackDay2Radio}"]`).prop('checked', true).checkboxradio('refresh');
            }
            return false;
        }

        /**
         * Resets form
         * @return {null}
         */
        clearForm() {
            const formElem = $('#registration-form');
            formElem.find('input:not([type=checkbox]):not([type=radio]), textarea, select').val('');
            formElem.find('input[type=radio]:not([name="mealPackDay2Radio"]):not([name="mealPackRadio"])')
                .prop('checked', false).checkboxradio('refresh');
        }

        /**
         * Handles all field change events.
         * @param  {Object} event
         * @return {boolean}
         */
        onChange(evt) {
            let changed = {
                name: '',
                value: ''
            };
            if ('currentTarget' in evt) {
                changed.name = $(evt.currentTarget).attr('name');
                changed.value = $(evt.currentTarget).val();
            };
            switch (changed.name) {
                case 'conferenceId':
                    if ('value' in changed && changed.value && changed.value !== '') {
                        const savedCookie = conference.getCookie(changed.value);
                        if (savedCookie) {
                            this.registrationModel = JSON.parse(savedCookie);
                            this.updateForm(this.registrationModel);
                        } else {
                            // clear the form
                            this.clearForm();
                            $('input[name="conferenceId"]').val(changed.value);
                        }
                    }

                    break;
                default:
                    // do nothing as for right now.
            }
            return false;
        }

        /**
         * Closes our failed modal
         * @param  {Object} event
         * @return {boolean}
         */
        onCloseRegistrationFailedModal(event) {
            $('.popup-overlay, .popup-content').removeClass("active");
            $('.popup-overlay .message').text('');
            return false;
        }

        /**
         * Opens our failed modal
         * @param  {Object} error
         * @return {null}
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
         * Handles the submission
         * @param e
         * @returns {boolean}
         */
        onSubmit(e) {
            let data = {};
            $($(e.currentTarget).serializeArray()).each(function(index, obj) {
                data[obj.name] = obj.value;
            });
            const {
                morningRadio,
                afternoonRadio,
                eveningRadio,
                mealPackRadio,
                mealPackDay2Radio,
            } = data;
            const error = RegistrationController.validateWorkshops({
                morningRadio,
                afternoonRadio,
                eveningRadio,
                mealPackRadio,
                mealPackDay2Radio,
            });

            data.conferenceId = data.conferenceId || this.defaultConferenceId;

            if (error) {
                this.showRegistrationFailedModal(error);
                // conference.alert(error.message, 'danger');
                return false;
            }

            conference.setCookie(data.conferenceId, JSON.stringify(data), 30);

            $.ajax({
                url: `/registration`,
                type: "POST",
                data: data,
                success: (response) => {
                    if (response && 'registrant' in response) {
                        window.location.href = `/thankyou?id=${ response.registrant.id }`;
                    } else {
                        console.error(response);
                        if (err && 'responseText' in err) {
                            this.showRegistrationFailedModal({ message: 'Unknown server error' });
                        }
                    }
                },
                error: (err) => {
                    console.error(err);
                    if (err && 'responseText' in err) {
                        this.showRegistrationFailedModal({ message: err.responseText });
                    }
                },
            });

            return false;
        }

        /**
         * Private for checking if workshop selection is valid.
         * @param selections
         * @returns {boolean}
         */
        static validateWorkshops(selections) {
            let error = false;
            if (selections.morningRadio === 'discovery' && selections.afternoonRadio) {
                error = { message: 'Discovery includes all afternoon workshops: pitching, power company simulation, and exit strategy.' }
            } else if (selections.afternoonRadio && selections.afternoonRadio === 'exitStrategy' && selections.eveningRadio !== 'sales') {
                error = { message: 'Attendees who select Exit Strategy for their afternoon workshops must also choose Sales for the Evening workshops.' }
            } else if (selections.eveningRadio === 'sales' && selections.afternoonRadio !== 'exitStrategy') {
                error = { message: 'Attendees who select Evening session workshop Sales must also take afternoon session workshop Exit Strategy.' }
            } else if (selections.morningRadio === 'discovery' && selections.eveningRadio === 'sales') {
                error = { message: 'Discovery includes all Evening workshops: Sales.' }
            }
            return error;
        }
    }

    conference.controller = new RegistrationController();

})(window.conference);