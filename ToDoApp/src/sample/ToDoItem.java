package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Thomas on 24/02/2016.
 */
public class ToDoItem {
    private StringProperty description;

    public ToDoItem(String description){
        this.description = new SimpleStringProperty(description);
    }

    public StringProperty getDescription() {
        return description;
    }

    public void setDescription(StringProperty description) {
        this.description = description;
    }

    public void setDescription(String description) {this.description.setValue(description);}
}
