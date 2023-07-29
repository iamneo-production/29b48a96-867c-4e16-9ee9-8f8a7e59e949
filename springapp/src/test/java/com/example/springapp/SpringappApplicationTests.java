package com.example.springapp;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.springapp.model.Appointment;
import com.example.springapp.model.Billing;
import com.example.springapp.model.Inventory;
import com.example.springapp.model.MedicalRecord;
import com.example.springapp.model.Patient;
import com.example.springapp.model.Pharmacy;
import com.example.springapp.model.Staff;
import com.example.springapp.service.AppointmentService;
import com.example.springapp.service.BillingService;
import com.example.springapp.service.InventoryService;
import com.example.springapp.service.MedicalRecordService;
import com.example.springapp.service.PatientService;
import com.example.springapp.service.PharmacyService;
import com.example.springapp.service.StaffService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
public class SpringappApplicationTests {

	
	 @Autowired
	    private MockMvc mockMvc;

	    private static final LocalDate date=LocalDate.of(2023, 05, 05);
	    
	    private static final LocalTime time=LocalTime.of(9,30);
	    
	    @Mock
	    private AppointmentService appointmentService;
	    
	    @Mock
	    private InventoryService InventoryService;
	    
	    @Mock
	    private BillingService billingService;
	    
	    @Mock
	    private MedicalRecordService mrservice;
	    
	    @Mock
	    private PatientService patientservice;
	    
	    @Mock
	    private PharmacyService pharmacyService;
	    
	    @Mock
	    private StaffService staffservice;
	    
	    
	    //AppointmentControllerTest
	    Appointment mockAppointment1= new Appointment(1L,2L,3L,date,time,"1 hour","Active");
	    Appointment mockAppointment2= new Appointment(2L,3L,4L,date,time,"1 hour","Active");
	    
	    List<Appointment> mockAppointmentList= Arrays.asList(mockAppointment1,mockAppointment2);
	    Optional<Appointment> optinalMockAppointment= Optional.ofNullable(mockAppointment1);

	    
	   
	    
	    @Test
	    public void testGetAppointmentAll() throws Exception {
	    	
	        Mockito.when(appointmentService.getAllAppointment()).thenReturn(mockAppointmentList);
	        mockMvc.perform(MockMvcRequestBuilders.get("/appointment"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(print())
	                .andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$").isArray())
					.andReturn();
	    }	
	    
	    @Test
	    public void testDeleteAppointmentById() throws Exception {
	        
	       String id="1";
	       mockMvc.perform(delete("/appointment")
	                .param("id", id))
					.andExpect(status().isOk())
					.andReturn();
	    }
	    
	    
	    //BillingControllerTest
	    Billing mockValue=new Billing(1L,3L,date,"Description",10L);
	    Billing mockValue1=new Billing(1L,3L,date,"Description",10L);

	    
        List<Billing> list=Arrays.asList(mockValue,mockValue1);
	    
	    String s1 = "{\"patientId\":1,\"treatment_description\":\"fever\",\"amount\":20L}";

	    Optional<Billing> optinalMock= Optional.ofNullable(mockValue);

	    
	    @Test
	    public void testGetAllBilling() throws Exception {
	        
	        Mockito.when(billingService.getAllBilling()).thenReturn(list);

	        mockMvc.perform(MockMvcRequestBuilders.get("/billing"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(print())
            .andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$").isArray())
			.andReturn();
	        
	    }
	    
	    //InventoryControllerTest
	    
	    Inventory inventory=new Inventory(1L,"name",200L,"category",20L,"supplier");
	    Inventory inventory1=new Inventory(2L,"name1",20L,"category",20L,"supplier");
        List<Inventory> inventoryList=Arrays.asList(inventory1,inventory);
	    
	    String st = "{\"id\":1,\"name\":\"Medicine\",\"quantity\":20L,\"category\":\"fever\",\"price\":20L,\"supplier\":\"Para\"}";
	    String st1 = "{\"name\":\"Medicine\",\"quantity\":20L,\"category\":\"fever\",\"price\":20L,\"supplier\":\"Para\"}";

	    
	    Optional<Inventory> optinalMockInventory= Optional.ofNullable(inventory);


	    @Test
	    public void testUpdateInventory() throws Exception {

	        mockMvc.perform(MockMvcRequestBuilders.put("/inventory")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(inventory)))
	                .andExpect(MockMvcResultMatchers.status().isCreated());

	    }

	    
	    Patient patient=new Patient(1L,"abc",30L,"female","Chennai","98989889","abc@gmail.com","nil","no");
	    Patient patient1=new Patient(2L,"qee",30L,"female","Chennai","98989889","qwe@gmail.com","nil","no");

	    
        List<Patient> listPatient=Arrays.asList(patient,patient1);
	    
	    Optional<Patient> optinalMockPatient= Optional.ofNullable(patient);
	   
	    @Test
	    public void testGetPatientAll() throws Exception {
	    	
	        Mockito.when(patientservice.getAllPatient()).thenReturn(listPatient);

	        mockMvc.perform(get("/patient"))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(print())
            .andExpect(content().contentType("application/json"))
   			.andExpect(jsonPath("$").isArray())
   			.andReturn();
	    }

	    @Test
	    public void testCreatePatient() throws Exception {
	    
	        mockMvc.perform(MockMvcRequestBuilders.post("/patient")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(patient)))
	                .andExpect(MockMvcResultMatchers.status().isCreated());

	    }
	    
	    //StaffControllerTest
	    
	    Staff mockValueStaff=new Staff(1L,"abc",30L,"female","chennai","98989889","Doctor","abcAgmail.com",10L,"benefits");
	    Staff mockValueStaff1=new Staff(2L,"abcg",30L,"female","chennai","989","Doctor","abcgAgmail.com",15L,"benefits");

	    
        List<Staff> lisStafft=Arrays.asList(mockValueStaff,mockValueStaff1);
	    
	    // String st = "{\"id\":1,\"name\":\"Medicine\",\"quantity\":20L,\"category\":\"fever\",\"prive\":20L,\"supplier\":\"Para\"}";

	    Optional<Staff> optinalMockStaff= Optional.ofNullable(mockValueStaff);

	    @Test
	    public void testGetStaffById() throws Exception {
	    	
	
	        long Id = 1L;

	     given(staffservice.getStaffById(Id)).willReturn(optinalMockStaff);
	     mockMvc.perform(get("/staff")
	    		 .param("id", "1"))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(print())
         .andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$").isArray())
			.andReturn();
	    }
	   

	    @Test
	    public void testCreateStaff() throws Exception {
	    
	     //  Mockito.when(InventoryService.saveInventory(inventory)).thenReturn(inventory);
	        mockMvc.perform(MockMvcRequestBuilders.post("/staff")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(mockValueStaff)))
	                .andExpect(MockMvcResultMatchers.status().isCreated());

	    }
	    
	    //MedicalRecordControllerTest
	    
	    MedicalRecord mockValueMr=new MedicalRecord(1L,2L,3L,date,"Fever","Paracetomol","next week visit again");
	    MedicalRecord mockValue1Mr=new MedicalRecord(1L,2L,3L,date,"Fever","Paracetomol","next week visit again");

	    
        List<MedicalRecord> listMr=Arrays.asList(mockValueMr,mockValue1Mr);
	   
	     
	    Optional<MedicalRecord> optinalMockMr= Optional.ofNullable(mockValueMr);

	    @Test
	    public void testGetMedicalRecordById() throws Exception {
	    	
	
	        long Id = 1L;
	        String id="1";

	     given(mrservice.getMedicalRecordById(Id)).willReturn(optinalMockMr);
	     mockMvc.perform(get("/medical-records")
	    		 .param("id", id))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(print())
	                .andExpect(content().contentType("application/json"))
	       			.andExpect(jsonPath("$").isArray())
	       			.andReturn();
	    }
	    
	    
	    Pharmacy pharmacy=new Pharmacy(1L,1L,"paracetomal","200mg",date,123L);
	    Pharmacy pharmacy2=new Pharmacy(2L,2L,"paracomal","20mg",date,123L);

        List<Pharmacy> pharmacyList=Arrays.asList(pharmacy,pharmacy2);
	    
	    
	    @Test
	    public void testGetPharmacyAll() throws Exception {
	    	
	        Mockito.when(pharmacyService.getAllPharmacy()).thenReturn(pharmacyList);

	        // Act
	        mockMvc.perform(get("/pharmacy"))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(print())
            .andExpect(content().contentType("application/json"))
   			.andExpect(jsonPath("$").isArray())
   			.andReturn();
	    }

	    private String asJsonString(Object object) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();                                   
	        return objectMapper.writeValueAsString(object);
	    }

	  
}
