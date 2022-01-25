package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.DukeException;

/**
 * Represents a Command object that will cause the app to
 * terminate its current run.
 */
public class ExitCommand extends Command {

    /**
     * Does nothing.
     *
     * @param stg The storage object to use file writing methods.
     * @param ui The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        return;
    }

    /**
     * Signifies to the app to terminate its current run.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}