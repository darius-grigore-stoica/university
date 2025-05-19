import React from 'react';

const TaskList = ({ tasks, onTaskComplete }) => {
  if (tasks.length === 0) return <p>No tasks assigned.</p>;

  return (
    <ul>
      {tasks.map((task) => (
        <li key={task.id}>
          <span
            style={{
              textDecoration: task.status === 'completed' ? 'line-through' : 'none',
            }}
          >
            {task.description}
          </span>
          {task.status !== 'completed' && (
            <button onClick={() => onTaskComplete(task.id)} className='complete-button'>
              Mark as Completed
            </button>
          )}
        </li>
      ))}
    </ul>
  );
};

export default TaskList;
