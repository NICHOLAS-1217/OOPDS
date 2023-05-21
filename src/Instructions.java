public class Instructions {
    // create string array for choices
    private String[] choices;
    // define Menu class
    public Instructions(String[] choices){
        this.choices = choices;
    }
    // display function
    public void display(){
        // create for loop to loop the choices 
        for(int i = 0; i < choices.length; i++){
            System.out.println(i + 1 +"." + choices[i]);
        }
    }
}
