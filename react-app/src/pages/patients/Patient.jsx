import React, { useEffect, useState } from 'react'
import '../styles.css';
import axios from 'axios';
import Swal from 'sweetalert2';
import DashboardHeader from '../../components/DashboardHeader';
const Patient = () => {
    const [patients, setPatients] = useState([]);
    const [edit, setEdit] = useState(false);
    const [add, setAdd] = useState(false);
    const [patientId, setPatientId] = useState('')
    const [name, setName] = useState('')
    const [age, setAge] = useState('')
    const [gender, setGender] = useState('')
    const [address, setAddress] = useState('')
    const [phone, setPhone] = useState('')
    const [email, setEmail] = useState('')
    const [medicalHistory, setMedicalHistory] = useState('')
    const [treatmentPlan, setTreatmentPlan] = useState('')
    const [searchQuery, setSearchQuery] = useState('')
    const fetchPatients = async () => {
        await axios.get('https://8080-ddeaddfaafedbeeafbdefaebabceebadffeaeaadbdbabf.project.examly.io/patient').then((response) => {
            setPatients(response.data);
        })

    }
    useEffect(() => {
        fetchPatients();
    }, [])
    const SubmitDelete = async (id) => {
        await axios.delete(`https://8080-ddeaddfaafedbeeafbdefaebabceebadffeaeaadbdbabf.project.examly.io/patient/${id}`).then(() => {
            fetchPatients();
        })
    }
    
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
                SubmitDelete(id);
                Swal.fire({
                    icon: 'success',
                    text: "Success",
                    title: 'Patient Deleted',
                    showConfirmButton: false,
                    timer: 1000
                })
            }
        })
    }
    const handleEdit = (patient) => {
        setEdit(true);
        setName(patient.name)
        setAddress(patient.address)
        setAge(patient.age)
        setGender(patient.gender)
        setPatientId(patient.id)
        setEmail(patient.email)
        setPhone(patient.phone)
        setTreatmentPlan(patient.treatmentPlan)
        setMedicalHistory(patient.medicalHistory);

    }
    const handleNewPatient = (e) => {
        e.preventDefault();
        setAdd(true);
        setEdit(false);
        clearFields();
    }
    const handleBack = (e) => {
        e.preventDefault();
        setAdd(false);
        setEdit(false);
        clearFields();
    }
    const handleView = (patient) => {
        Swal.fire({
            icon: 'info',
            title: 'Patient Details',
            html: `
            <b>Patient ID:</b> ${patient.id}<br/>
            <b>Name:</b> ${patient.name}<br/>
            <b>Age:</b> ${patient.age}<br/>
            <b>Gender:</b> ${patient.gender}<br/>
            <b>Address:</b> ${patient.address}<br/>
            <b>Phone:</b> ${patient.phone}<br/>
            <b>Email:</b> ${patient.email}<br/>
            <b>Medical History:</b> ${patient.medicalHistory}<br/>
            <b>Treatment Plan:</b> ${patient.treatmentPlan}<br/>
          `,
            confirmButtonText: 'Close',
            showConfirmButton: true,
        });
    };

    const handleEditSubmit = async (e) => {
        e.preventDefault();

        
        const updatedPatient = {
            id: patientId,
            name: name,
            age: age,
            gender: gender,
            address: address,
            phone: phone,
            email: email,
            medicalHistory: medicalHistory,
            treatmentPlan: treatmentPlan
        }
        await axios.put(`https://8080-ddeaddfaafedbeeafbdefaebabceebadffeaeaadbdbabf.project.examly.io/patient`, updatedPatient).then(() => {
            Swal.fire({
                icon: 'success',
                title: 'Patient Updated Successfully',
                showConfirmButton: false,
                timer: 1000
            })
            setEdit(false);
            clearFields();
            fetchPatients();
        }).catch((err) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Something went wrong.Please check the details',
            })
            console.log(err);
        })
    }
    const handleAddSubmit = async (e) => {
        e.preventDefault();
        
        const patient = {
            name: name,
            age: age,
            gender: gender,
            address: address,
            phone: phone,
            email: email,
            medicalHistory: medicalHistory,
            treatmentPlan: treatmentPlan
        }
        await axios.post('https://8080-ddeaddfaafedbeeafbdefaebabceebadffeaeaadbdbabf.project.examly.io/patient', patient).then((response) => {
            Swal.fire({
                icon: 'success',
                title: 'Patient Added',
                showConfirmButton: false,
                timer: 1000
            })
            fetchPatients();
            setAdd(false);
            clearFields();
        }).catch((err)=>{
            console.log(err)
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Something went wrong.Please check the details',
            })
        })
    }
    const clearFields = () => {
        setName('')
        setAddress('')
        setAge('')
        setGender('')
        setPatientId('')
        setEmail('')
        setPhone('')
        setTreatmentPlan('')
        setMedicalHistory('')
    }
    return (
        <div>
            <div className='dashboard-content'>
                {!edit && !add && <DashboardHeader btnText="New Patient" onClick={handleNewPatient} />}
                {(edit || add) && <DashboardHeader btnText="Back to Patients" onClick={handleBack} />}
                {!edit && !add && <div className='dashboard-content-container'>
                    <div className='dashboard-content-header'>
                        <h2>Patients List</h2>
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
                                <th>NAME</th>
                                <th>AGE</th>
                                <th>GENDER</th>
                                {/* <th>ADDRESS</th> */}
                                <th>PHONE</th>
                                {/* <th>EMAIL</th> */}
                                {/* <th>MEDICAL HISTORY</th> */}
                                {/* <th>TREATMENT PLAN</th> */}
                                <th>Actions</th>
                            </tr>
                        </thead>
                        {patients.length !== 0 &&
                            <tbody>
                                {patients
                                    .filter(patient => {
                                        return (
                                            patient.id.toString().includes(searchQuery) || patient.name.toLowerCase().includes(searchQuery)
                                        );
                                    })
                                    .map((patient) => {
                                        return <tr key={patient.id}>
                                            <td><span>{patient.id}</span></td>
                                            <td><span>{patient.name}</span></td>
                                            <td><span>{patient.age}</span></td>
                                            <td><span>{patient.gender}</span></td>
                                            {/* <td><span>{patient.address}</span></td> */}
                                            <td><span>{patient.phone}</span></td>
                                            {/* <td><span>{patient.email}</span></td> */}
                                            {/* <td><span>{patient.medicalHistory}</span></td> */}
                                            {/* <td><span>{patient.treatmentPlan}</span></td> */}
                                            <td>
                                                <button onClick={() => handleEdit(patient)} className='edit-save-btn'>Edit</button>
                                                <button onClick={() => handleDelete(patient.id)} className='edit-back-btn'>Delete</button>
                                                <button className='view-btn' onClick={() => handleView(patient)}>View</button>
                                            </td>
                                        </tr>
                                    })}
                            </tbody>
                        }
                    </table>
                </div>}
                {edit &&
                    <div className='form-elements'>
                        <div className='dashboard-content-header'>
                            <h2>Edit Patient Details</h2>
                        </div>
                        <div>
                            <form onSubmit={handleEditSubmit}>
                                <label htmlFor="pid">Patient Id</label>
                                <br />
                                <input type="number" id="pid" className='form-inputs' value={patientId} onChange={(e) => setPatientId(e.target.value)} readOnly />
                                <br />
                                <label htmlFor="pname" className='form_label'>Name</label>
                                <br />
                                <input type="text" id="pname" className='form-inputs' value={name} onChange={(e) => setName(e.target.value)} required />
                                <br />
                                <label htmlFor="page" className='form_label'>Age</label>
                                <br />
                                <input type="number" id="page" className='form-inputs' value={age} onChange={(e) => setAge(e.target.value)} required />
                                <br />
                                <label htmlFor="gen" className='form_label'>Gender</label>
                                <br />
                                <input type="text" name="" id="gen" className='form-inputs' value={gender} onChange={(e) => setGender(e.target.value)} required />
                                <br />
                                <label htmlFor="addr" className='form_label'>Address</label>
                                <br />
                                <input type="text" id="addr" className='form-inputs' value={address} onChange={(e) => setAddress(e.target.value)} required />
                                <br />
                                <label htmlFor="ph" className='form_label'>Phone</label>
                                <br />
                                <input type="text" id="ph" className='form-inputs' value={phone} onChange={(e) => setPhone(e.target.value)} required />
                                <br />
                                <label htmlFor="em" className='form_label'>Email</label>
                                <br />
                                <input type="text" id="em" className='form-inputs' value={email} onChange={(e) => setEmail(e.target.value)} required />
                                <br />
                                <label htmlFor="medhis" className='form_label'>Medical History</label>
                                <br />
                                <input type="text" name="" id="medhis" className='form-inputs' value={medicalHistory} onChange={(e) => setMedicalHistory(e.target.value)} required />
                                <br />
                                <label htmlFor="tplan" className='form_label'>Treatment Plan</label>
                                <br />
                                <input type="text" name="" id="tplan" className='form-inputs' value={treatmentPlan} onChange={(e) => setTreatmentPlan(e.target.value)} required />
                                <br />
                                <button type="submit" className='save-btn'>Update</button>
                                <button className='back-btn' onClick={handleBack}>Cancel</button>

                            </form>
                        </div>

                    </div>
                }
                {add &&
                    <div className='form-elements'>
                        <div className='dashboard-content-header'>
                            <h2>Patient Registration</h2>
                        </div>
                        <div>
                            <form onSubmit={handleAddSubmit}>
                                <label htmlFor="pname" className='form_label'>Name</label>
                                <br />
                                <input type="text" id="pname" className='form-inputs' value={name} onChange={(e) => setName(e.target.value)} required />
                                <br />
                                <label htmlFor="page" className='form_label'>Age</label>
                                <br />
                                <input type="number" id="page" className='form-inputs' value={age} onChange={(e) => setAge(e.target.value)} required />
                                <br />
                                <label htmlFor="gen" className='form_label'>Gender</label>
                                <br />
                                <input type="text" id="gen" className='form-inputs' value={gender} onChange={(e) => setGender(e.target.value)} required />
                                <br />
                                <label htmlFor="addr" className='form_label'>Address</label>
                                <br />
                                <input type="text" id="addr" className='form-inputs' value={address} onChange={(e) => setAddress(e.target.value)} required />
                                <br />
                                <label htmlFor="ph" className='form_label'>Phone</label>
                                <br />
                                <input type="text" id="ph" className='form-inputs' value={phone} onChange={(e) => setPhone(e.target.value)} required />
                                <br />
                                <label htmlFor="em" className='form_label'>Email</label>
                                <br />
                                <input type="text" id="em" className='form-inputs' value={email} onChange={(e) => setEmail(e.target.value)} required />
                                <br />
                                <label htmlFor="medhis" className='form_label'>Medical History</label>
                                <br />
                                <input type="text" id="medhis" className='form-inputs' value={medicalHistory} onChange={(e) => setMedicalHistory(e.target.value)} required />
                                <br />
                                <label htmlFor="tplan" className='form_label'>Treatment Plan</label>
                                <br />
                                <input type="text" id="tplan" className='form-inputs' value={treatmentPlan} onChange={(e) => setTreatmentPlan(e.target.value)} required />
                                <br />
                                <button type="submit" className='save-btn'>Save</button>
                                <button className='back-btn' onClick={handleBack}>Cancel</button>
                            </form>
                        </div>
                    </div>
                }
            </div>
        </div>
    )
}

export default Patient
