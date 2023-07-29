import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Swal from 'sweetalert2';
import '../styles.css';
import DashboardHeader from '../../components/DashboardHeader';

const Appointment = () => {
  const [appointments, setAppointments] = useState([]);
  const [edit, setEdit] = useState(false);
  const [add, setAdd] = useState(false);
  const [id, setId] = useState('');
  const [patientId, setPatientId] = useState('');
  const [doctorId, setDoctorId] = useState('');
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');
  const [duration, setDuration] = useState('');
  const [status, setStatus] = useState('');
  const [searchQuery, setSearchQuery] = useState('');

  const fetchAppointments = async () => {
    await axios.get('https://8080-feceaeedabbcfbdefaebabceebadffeaeaadbdbabf.project.examly.io/appointment').then((response) => {
      setAppointments(response.data);
    }).catch((err)=>{
      console.log(err);
    })
  };

  useEffect(() => {
    fetchAppointments();
  }, []);

  const submitDelete = async (id) => {
    await axios.delete(`https://8080-ddeaddfaafedbeeafbdefaebabceebadffeaeaadbdbabf.project.examly.io/appointment/${id}`).then(() => {
      fetchAppointments();
    });
  };

  const handleDelete = (id) => {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showDenyButton: true,
        confirmButtonText: 'Yes',
        denyButtonText: `No`,
    }).then((result) => {
        if (result.isConfirmed) {
            submitDelete(id);
            Swal.fire({
                icon: 'success',
                text: "Success",
                title: 'Record Deleted',
                showConfirmButton: false,
                timer: 1000
            })
        }
    })
}

  const handleEdit = (appointment) => {
    setEdit(true);
    setId(appointment.id);
    setPatientId(appointment.patientId);
    setDoctorId(appointment.doctorId);
    setDate(appointment.date);
    setTime(appointment.time);
    setDuration(appointment.duration);
    setStatus(appointment.status);
  };

  const handleNewAppointment = (e) => {
    e.preventDefault();
    setAdd(true);
    setEdit(false);
    clearFields();
  };

  const handleBack = (e) => {
    e.preventDefault();
    setAdd(false);
    setEdit(false);
    clearFields();
  };

  const handleEditSubmit = async (e) => {
    e.preventDefault();
    const updatedAppointment = {
      id: id,
      patientId: patientId,
      doctorId: doctorId,
      date: date,
      time: time,
      duration: duration,
      status: status,
    };
    await axios.put('https://8080-feceaeedabbcfbdefaebabceebadffeaeaadbdbabf.project.examly.io/appointment', updatedAppointment).then(() => {
      Swal.fire({
        icon: 'success',
        text: 'Updated',
        title: 'Appointment Updated Successfully',
        showConfirmButton: false,
        timer: 1000,
      });
      setEdit(false);
      clearFields();
      fetchAppointments();
    }).catch((err) => {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Something went wrong.Please check the details',
    })
      console.log(err);
    });
  };

  const handleAddSubmit = async (e) => {
    e.preventDefault();
    const appointment = {
      patientId: patientId,
      doctorId: doctorId,
      date: date,
      time: time,
      duration: duration,
      status: status,
    };
    await axios.post('https://8080-ddeaddfaafedbeeafbdefaebabceebadffeaeaadbdbabf.project.examly.io/appointment', appointment).then((response) => {
      Swal.fire({
        icon: 'success',
        title: 'Appointment Added',
        showConfirmButton: false,
        timer: 1000,
      });
      fetchAppointments();
      setAdd(false);
      clearFields();
    }).catch((err) => {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Something went wrong.Please check the details',
    })
      console.log(err);
    });
  };

  const clearFields = () => {
    setId('');
    setPatientId('');
    setDoctorId('');
    setDate('');
    setTime('');
    setDuration('');
    setStatus('');
  };

  return (
    <div>
      <div className="dashboard-content">
        {!edit && !add && <DashboardHeader btnText="New Appointment" onClick={handleNewAppointment} />}
        {(edit || add) && <DashboardHeader btnText="Back to Appointments" onClick={handleBack} />}
        {!edit && !add && (
          <div className="dashboard-content-container">
            <div className="dashboard-content-header">
              <h2>Appointment List</h2>
              <div className='dashboard-content-search'>
                <input
                  type='text'
                  placeholder='Search..'
                  className='dashboard-content-input'
                  value={searchQuery}
                  onChange={(e) => setSearchQuery(e.target.value)}
                  min="1"
                  inputMode="numeric"
                />
              </div>
            </div>
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Patient ID</th>
                  <th>Doctor ID</th>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Duration</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>

              </thead>
              {appointments.length !== 0 && (
                <tbody>
                  {appointments
                    .filter((appointment) => {
                      return (
                        appointment.id.toString().includes(searchQuery) ||
                        appointment.patientId.toString().includes(searchQuery)
                      );
                    })
                    .map((appointment) => {
                      return (
                        <tr key={appointment.id}>
                          <td><span>{appointment.id}</span></td>
                          <td><span>{appointment.patientId}</span></td>
                          <td><span>{appointment.doctorId}</span></td>
                          <td><span>{appointment.date}</span></td>
                          <td><span>{appointment.time}</span></td>
                          <td><span>{appointment.duration}</span></td>
                          <td><span>{appointment.status}</span></td>
                          <td>
                            <button onClick={() => handleEdit(appointment)} className="edit-save-btn">
                              Edit
                            </button>
                            <button onClick={() => handleDelete(appointment.id)} className="edit-back-btn">
                              Delete
                            </button>
                          </td>
                        </tr>
                      );
                    })}
                </tbody>
              )}
            </table>
          </div>
        )}
        {edit && (
          <div className="form-elements">
            <div className="dashboard-content-header">
              <h2>Edit Appointment Details</h2>
            </div>
            <form onSubmit={handleEditSubmit}>
              <label htmlFor="id" className='form_label'>Appointment ID:</label><br />
              <input className='form-inputs' id="id" type="number" value={id} readOnly />
              <br />
              <label htmlFor="patientid" className='form_label'>Patient ID:</label><br />
              <input className='form-inputs' id="patientid" type="number" value={patientId} readOnly />
              <br />
              <label htmlFor="doctorid" className='form_label'>Doctor ID:</label><br />
              <input className='form-inputs' id="doctorid" type="number" value={doctorId} readOnly />
              <br />
              <label htmlFor="appdate" className='form_label'>Date:</label><br />
              <input className='form-inputs' id="appdate" type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
              <br />
              <label htmlFor="apptime" className='form_label'>Time:</label><br />
              <input className='form-inputs' type="text" id="apptime" value={time} onChange={(e) => setTime(e.target.value)} required />
              <br />
              <label htmlFor="duration" className='form_label'>Duration:</label><br />
              <input className='form-inputs' id="duration" type="text" value={duration} onChange={(e) => setDuration(e.target.value)} required />
              <br />
              <label htmlFor="status" className='form_label'>Status:</label><br />
              <input className='form-inputs' id="status" type="text" value={status} onChange={(e) => setStatus(e.target.value)} required />
              <br />
              <button type="submit" className="save-btn">Update</button>
              <button className="back-btn" onClick={handleBack}>Cancel</button>
            </form>
          </div>
        )}
        {add && (
          <div className="form-elements">
            <div className="dashboard-content-header">
              <h2>Add Appointment</h2>
            </div>
            <form onSubmit={handleAddSubmit}>
              <label htmlFor="patientid" className='form_label'>Patient ID:</label><br />
              <input className='form-inputs' id="patientid" type="text" value={patientId} onChange={(e) => setPatientId(e.target.value)} required />
              <br />
              <label htmlFor="doctorid" className='form_label'>Doctor ID:</label><br />
              <input className='form-inputs' id="doctorid" type="text" value={doctorId} onChange={(e) => setDoctorId(e.target.value)} required />
              <br />
              <label htmlFor="appdate" className='form_label'>Date:</label><br />
              <input className='form-inputs' id="appdate" type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
              <br />
              <label htmlFor="apptime" className='form_label'>Time:</label><br />
              <input className='form-inputs' type="text" id="apptime" value={time} onChange={(e) => setTime(e.target.value)} required />
              <br />
              <label htmlFor="duration" className='form_label'>Duration:</label><br />
              <input className='form-inputs' type="text" id="duration" value={duration} onChange={(e) => setDuration(e.target.value)} required />
              <br />
              <label htmlFor="status" className='form_label'>Status:</label><br />
              <input className='form-inputs' type="text" id="status" value={status} onChange={(e) => setStatus(e.target.value)} required />
              <br />
              <button type="submit" className="save-btn">Save</button>
              <button className="back-btn" onClick={handleBack}>Cancel</button>
            </form>
          </div>
        )}
      </div>
    </div>
  );
};

export default Appointment;