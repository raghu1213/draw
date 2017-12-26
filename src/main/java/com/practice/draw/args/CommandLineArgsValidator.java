package com.practice.draw.args;

import com.practice.draw.common.Validator;

public abstract class CommandLineArgsValidator implements Validator<String[]> {

        @Override
        public boolean validate(String[] param) {
           throw new UnsupportedOperationException("The method must be overridden in base class.");
        }


    /** Provides a base functionality to test the parameters
     * All the values are validated for integer except first value in the array.
     * @param params
     * @param length
     * @return boolean
     */
        protected final boolean validate(String[] params, int length){
            boolean isValid = params.length ==length;
            if (isValid){
                for (int idx=1; idx< params.length; idx ++){
                    if (!this.isInteger(params[idx])){
                        isValid = false;
                        break;
                    }
                }
            }
            return isValid;
        }

        protected final boolean isInteger(String value) {
            try {
                Integer.parseUnsignedInt(value);
            } catch(NumberFormatException e) {
                return false;
            } catch(NullPointerException e) {
                return false;
            }
            return true;
        }



}
