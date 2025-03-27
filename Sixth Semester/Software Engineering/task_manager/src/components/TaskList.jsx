import React from 'react';

const TaskList = ({ tasks, onTaskComplete }) => {
  return (
    <div className="task-list">
      {tasks.length === 0 ? (
        <p>No tasks assigned.</p>
      ) : (
        <ul>
          {tasks.map((task) => (
            <li key={task.id} className={`task-item ${task.status}`}>
              <div className="task-info">
                <h3>{task.description}</h3>
                <p>Status: {task.status}</p>
              </div>
              {task.status === 'pending' && (
                <button
                  onClick={() => onTaskComplete(task.id)}
                  className="complete-button"
                >
                  Mark as Completed
                </button>
              )}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default TaskList;