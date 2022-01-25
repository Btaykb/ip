package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.DukeException;

import java.io.IOException;
import java.time.format.DateTimeParseException;


/**
 * Represents a Command object that will add new tasks.
 */
public class AddCommand extends Command {

    private String taskType;
    private String textInput;

    /**
     * Constructor for the AddCommand object.
     *
     * @param type The type of tasks to be added,
     * (Todo, Deadline, Event).
     * @param input The user's input containing the information
     * of the task to be added.
     */
    public AddCommand(String type, String input) {
        this.textInput = input;
        this.taskType = type;
    }

    /**
     * Signifies to the app to not terminate its current run.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Will add the provided task to the tasklist.
     *
     * @param stg The storage object to use file writing methods.
     * @param ui The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws IOException {
        if (this.taskType.equals("todo")) {
            try {
                Todo td = Todo.createTodo(this.textInput);
                tasks.addTask(td);
                stg.writeToFile(td.formatText() + "\n");
            } catch (DukeException e) {
                System.out.println("Please enter a valid description!");
                ui.showLine();
                return;
            }
        } else if (this.taskType.equals("deadline")) {
            try {
                Deadline dl = Deadline.createDeadline(this.textInput);
                tasks.addTask(dl);
                stg.writeToFile(dl.formatText() + "\n");
            } catch (DukeException e) {
                System.out.println("Please enter a valid description/date!");
                ui.showLine();
                return;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please enter a date (eg. /by 2019-12-12)!");
                ui.showLine();
                return;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter valid date in YYYY-MM-DD format!");
                ui.showLine();
                return;
            }
        } else {
            try {
                Event ev = Event.createEvent(this.textInput);
                tasks.addTask(ev);
                stg.writeToFile(ev.formatText() + "\n");
            } catch (DukeException e) {
                System.out.println("Please enter a valid description/date!");
                ui.showLine();
                return;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please enter a date (eg. /at Monday 3-4pm)!");
                ui.showLine();
                return;
            }
        }
        ui.showCount(tasks);
        ui.showLine();
    }
}
