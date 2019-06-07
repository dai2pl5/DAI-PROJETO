package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Sensor;
import com.boot.payload.ApiResponse;
import com.boot.repository.SensorRepository;

import java.util.ArrayList;
import java.util.Iterator;


@RestController
@RequestMapping("/sensor")
public class SensorController {
	
	@Autowired
	private SensorRepository sensorRepository;
	
	@GetMapping("/todos")
	public List<Sensor> dados(){
		
		List<Sensor> listaDados = sensorRepository.findAll();;
		 
		return listaDados;
	}
	
	
	
	@GetMapping("/sensorHumidade/{value}")
	public List<Sensor> execute(@PathVariable(value = "value") int value){
		
	List<Sensor> listaHumidade = sensorRepository.findByNameAndValue("sensor humidade", value );
		 
		return listaHumidade;
	}
	
	@GetMapping("/ordem/{id}")
	public String[] getSensorsValues(@PathVariable(value="id") int id){
		//List<Sensor> listaOrdem = sensorRepository.findByNameLikeOrderByISODATADesc("sensor humidade");
		List<Sensor> listaHumidade = sensorRepository.findByIdInsuranceAndNameOrderByISODATADesc(id, "sensor humidade");
		List<Sensor> listaTemperatura = sensorRepository.findByIdInsuranceAndNameOrderByISODATADesc(id, "sensor temperatura");
		List<Sensor> listaPermanencia = sensorRepository.findByIdInsuranceAndNameOrderByISODATADesc(id, "sensor permanência");
		List<Sensor> listaMovimento = sensorRepository.findByIdInsuranceAndNameOrderByISODATADesc(id, "sensor movimento");
		List<Sensor> listaGas = sensorRepository.findByIdInsuranceAndNameOrderByISODATADesc(id, "sensor gas");
		List<Sensor> listaInundacao = sensorRepository.findByIdInsuranceAndNameOrderByISODATADesc(id, "sensor agua");
		String[] value = {listaHumidade.get(0).getValue(), listaTemperatura.get(0).getValue(), listaPermanencia.get(0).getValue(), listaMovimento.get(0).getValue(), listaGas.get(0).getValue(),
				listaInundacao.get(0).getValue()};
		
		return value;
	}
	
	@GetMapping("/getSensores")
	public ResponseEntity<List<Sensor>> getSensores(){
		
		List<Sensor> sensors = sensorRepository.findAllByOrderByISODATADesc();
		List<Sensor> finalSensors = new ArrayList<Sensor>();
		for(int i = 0; i < 25; i++) {
			finalSensors.add(sensors.get(i));
		}
		
		return ResponseEntity.ok().body(finalSensors);
	}
	
	@PutMapping("/setMovimento")
	public ResponseEntity<ApiResponse> setMovimento(){
		
		List<Sensor> listaMovimento = sensorRepository.findByValueAndName(1, "sensor movimento");
		
		for(Sensor sensor : listaMovimento) {
			sensor.setValue("0");
			sensorRepository.save(sensor);
		}
		return ResponseEntity.ok(new ApiResponse(true, "Ok"));
	}
	
	
	/*public void todos() {
		System.out.println("sensor found with findAll():");
		System.out.println("-------------------------------");
		for (sensor sensor : repository.findAll()) {
			System.out.println(sensor);
		}
		
	}
	*/

}