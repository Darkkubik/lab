import logo from './logo.svg';
import React, {} from 'react';
import './App.css';
import "bootstrap/dist/css/bootstrap.css";
import Request from "./Services/Request";
import { Navbar, Nav, Dropdown, DropdownButton, Tabs, Table, Tab } from "react-bootstrap";



class App extends React.Component {
    constructor() {
        super();
        this.state = {
            activeGroup: 0,
            students: null,
            sortStudents: null,
            plan: null,
            journal: null,
            groups: null
        };
    }

    componentDidMount() {
        Request.getStudents().then((students) => {
            this.setState({students: students});
        });
        Request.getJournal().then((journal) => {
            this.setState({journal: journal});
        });
        Request.getGroup().then((groups) => {
            this.setState({groups: groups});
        });
        Request.getStudentOrder().then((students) => {
            this.setState({students: students});
        });
    }

    render() {
        if (this.state.activeGroup === 0) {
            return (
                <div className="App">
                    {this.state.students && this.state.groups ? <StTable
                        students={this.state.students}
                        groups={this.state.groups}/> : null}
                    <button onClick={() => {
                        Request.getStudents().then((students) => {
                            this.setState({students: students});
                        });
                        this.setState({activeGroup: 0});
                    }}>Студенты
                    </button>
                    <button onClick={() => {
                        this.setState({activeGroup: 2});
                    }}>Журнал
                    </button>
                    <button onClick={() => {
                        Request.getStudentOrder().then((students) => {
                            this.setState({students: students});
                        });
                    }}>Сортировка по фамилии
                    </button>
                </div>
            );
        }

        if (this.state.activeGroup === 2) {
            return (
                <div className="App">
                    {this.state.journal ? <JournalTable
                        journal={this.state.journal}/> : null}
                    <button onClick={() => {
                        this.setState({activeGroup: 0});
                    }}>Студенты
                    </button>
                    <button onClick={() => {
                        this.setState({activeGroup: 2});
                    }}>Журнал
                    </button>
                    <button onClick={() => {
                        Request.getJournalOrder().then((journal) => {
                            this.setState({journal: journal});
                        });
                    }}>Сортировка по убыванию суммы оценок
                    </button>
                </div>
            );
        }
    }
}

class StTable extends React.Component
{
    render() {
        return <Table bordered>
            <thead>
            <tr>
                <th>#</th>
                <th>ФИО</th>
                <th>Номер Группы</th>
            </tr>
            </thead>
            <tbody>
            {this.props.students.map((student, index) => {
                return <tr>
                    <td>{index + 1} </td>
                    <td>{student.surname} {student.name} {student.second_name}</td>
                    <td>{student.study_group_id}</td>
                    <td>
                        <DropdownButton title={"Перевести в другую группу"}>
                            {this.props.groups.map((group, index) => {
                                if (group.id !== student.study_group_id) {
                                    return <Dropdown.Item
                                        eventKey={index}
                                        onClick={() => (fetch(`http://localhost:8080/student/`,{
                                            method: "POST",
                                            body: JSON.stringify(
                                                {
                                                    id: student.id,
                                                    surname: student.surname,
                                                    name: student.name,
                                                    second_name: student.second_name,
                                                    study_group_id: group.id }),
                                            headers:
                                                {
                                                    'Content-Type': 'application/json'
                                                },
                                        }).then(() => {
                                            alert('Перевод студента ' + student.surname + ' ' +
                                                student.name + ' ' +  student.second_name +' в группу ' + group.name +
                                                ' произведён');

                                        }))} >
                                        {group.name}
                                    </Dropdown.Item>
                                }})}

                        </DropdownButton>
                    </td>
                </tr>
            })}
            </tbody>
        </Table>
    }
}

class JournalTable extends React.Component
{
    render() {
        return <Table bordered>
            <thead>
            <tr>
                <th>ID Студента</th>
                <th>ID Плана обучения</th>
                <th>Оценка</th>
            </tr>
            </thead>
            <tbody>
            {this.props.journal.map((journal, index) => {
                return <tr>
                    <td>{journal.student_id}   </td>
                    <td>{journal.study_plan_id} </td>
                    <td style={{color: journal.mark_id !== 5 ? 'green' : 'red' }}>{journal.mark_id}</td>
                </tr>
            })}
            </tbody>
        </Table>
    }
}

export default App;

