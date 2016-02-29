package sample;

import DBManager.DBManager;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;


import java.util.ArrayList;

public class Controller{
    @FXML
    private TableView<ToDoItem> todoList;

    @FXML
    private TableColumn<ToDoItem,String> todoColumn;

    @FXML
    private TextField newTodo;

    private final ObservableList<ToDoItem> toDodata = FXCollections.observableArrayList();

    @FXML
    protected void initialize(){
        todoList.setItems(toDodata);
        todoColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());

        //set cells to text field if in edit mode
        todoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        todoColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ToDoItem, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ToDoItem, String> t) {
                        if(t.getNewValue().length()>0) {
                            ToDoItem toDoItem = (ToDoItem) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow());
                            DBManager.updateToDo(toDoItem, t.getNewValue());
                            toDoItem.setDescription(t.getNewValue());
                        }
                    }
                }
        );

        ArrayList<ToDoItem> toDoItems = DBManager.selectAllToDo();
        toDodata.addAll(toDoItems);
    }



    @FXML
    public void addRecord(ActionEvent actionEvent){
        if(newTodo.getText().length()>0) {
            ToDoItem aTodo = new ToDoItem(newTodo.getText());
            todoList.getItems().add(aTodo);
            newTodo.setText("");
            DBManager.addToDo(aTodo);
        }
    }

    @FXML
    public void deleteRecord(ActionEvent actionEvent){
        DBManager.deleteToDo(todoList.getSelectionModel().getSelectedItems().get(0));
        todoList.getItems().removeAll(todoList.getSelectionModel().getSelectedItems());

    }

}