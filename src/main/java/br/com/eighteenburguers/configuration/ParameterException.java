package br.com.eighteenburguers.configuration;

public class ParameterException extends Exception {


    private String parameter;

    public ParameterException (String parameter){
        this.parameter = parameter;
    }


    @Override
    public String getMessage() {
        return String.format("Parameter %s not found", parameter);
    }
    
}
