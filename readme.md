# Financial control system for research projects

## About

This is a financial control system for research projects. It was developed using Java and MySQL as an assignment for the Object-Oriented Programming course.

## Database design

```mermaid
erDiagram
  projeto {
    int id PK
    varchar nome
    varchar professor
    double despesasCapitalPrevisto
    double despesasCapitalGasto
    double materialConsumoPrevisto
    double materialConsumoGasto
    double servicosPessoaFisicaPrevisto
    double servicosPessoaFisicaGasto
    double servicosPessoaJuridicaPrevisto
    double servicosPessoaJuridicaGasto
    double diarias_previsto
    double diariasGasto
    double passagensPrevisto
    double passagensGasto
  }
  despesa {
    int id PK
    int projeto_id FK
    varchar descricao
    int categoria
    double valor
  }
  projeto ||--o{ despesa : "tem"
```


## Screenshots

### Main screen

![Main screen](./screenshots/home.png)

### Register a new project

![Register a new project](./screenshots/register.png)

### List of projects

![List of projects](./screenshots/list-projects.png)

### Project overview

![Project overview](./screenshots/project-overview.png)

### List of expenses

![List of expenses](./screenshots/list-of-expenses.png)

## Practicing exception handling

### Error adding a new expense

![Error adding a new expense](./screenshots/exception-expense.png)

### Error adding a new project

![Error adding a new project](./screenshots/exception-registering-project.png)

### Error updating project information

![Error updating project information](./screenshots/exception-update-project.png)
