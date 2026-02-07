const express = require('express');
const app = express();
const port = 3000;

app.use(express.json());

const { body, validationResult } = require('express-validator');
const allowedFields = ['todo', 'completed', 'userId'];

let todos = [
  { id: 1, todo: 'Learn Express.js', completed: false, userId: 10 },
  { id: 2, todo: 'Do sth nice', completed: false, userId: 10 }
];

// GET all todos
app.get('/todos', (req, res) => {
  res.json(todos);
});

// GET single to do
app.get('/todos/:id', (req, res) => {
  const todo = todos.find(t => t.id == req.params.id);
  if (!todo) return res.status(404).json({ message: 'Todo not found' });
  res.json(todo);
});

// POST new to do
app.post(
  '/todos/add',
  [
    // express-validator field checks
    body('todo').isString().withMessage('todo must be a string'),
    body('completed').isBoolean().withMessage('completed must be a boolean'),
    body('userId').isInt().withMessage('userId must be an integer')
  ],
  (req, res) => {
    // Check for unexpected fields
    const keys = Object.keys(req.body);
    const invalidFields = keys.filter(k => !allowedFields.includes(k));

    if (invalidFields.length > 0) {
      return res.status(400).json({ message: "Unexpected fields: " + invalidFields.join(', ') });
    }

    // Check validation result
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      const simplifiedErrors = errors.array().map(err => ({ message: err.msg }));
      return res.status(400).json({ errors: simplifiedErrors });
    }


    // Create new to do
    const newTodo = { id: Date.now(), ...req.body };
    todos.push(newTodo);
    res.status(201).json(newTodo);
  }
);

// PUT (replace)
app.put('/todos/:id', (req, res) => {
  const index = todos.findIndex(t => t.id == req.params.id);
  if (index === -1) return res.status(404).json({ message: 'Todo not found' });
  todos[index] = { id: Number(req.params.id), ...req.body };
  res.json(todos[index]);
});

// DELETE
app.delete('/todos/:id', (req, res) => {
  const index = todos.findIndex(t => t.id == req.params.id);
  if (index === -1) return res.status(404).json({ message: 'Todo not found' });
  todos.splice(index, 1);
  res.status(204).send();
});

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
