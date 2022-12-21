package kr.co.ensmart.frameworkdemo.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ensmart.frameworkdemo.app.service.sample.SampleService;
import kr.co.ensmart.frameworkdemo.app.service.sample2.Sample2Service;
import kr.co.ensmart.frameworkdemo.common.dto.sample.Sample;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sample/api")
@RequiredArgsConstructor
public class SampleRestController {
	private final SampleService sampleService;
    private final Sample2Service sample2Service;
	
	@GetMapping("/retrieve-all-samples")
	public List<Sample> retrieveAllSamples() {
		return sampleService.retrieveAllSamples();
	}
	
    @GetMapping("/retrieve-all-samples-caller")
    public List<Sample> retrieveAllSamplesCaller() {
        return sampleService.retrieveAllSamplesCaller();
    }
    
    @GetMapping("/retrieve-all-samples-tx")
    public List<Sample> retrieveAllSamplestx() {
        return sampleService.retrieveAllSamplesTx();
    }
    
	@GetMapping("/{id}")
	public Sample retrieveAllSamples(@PathVariable Integer id) {
		return sampleService.retrieveSampleById(id);
	}
	
    @PostMapping("/registerTwo")
    public void registerTwo() {
        sampleService.registerTwo();
    }

    @PostMapping("/registerWithException")
    public void registerWithException() throws Exception {
        sampleService.registerWithException();
    }

    @PostMapping("/regWithException")
    public void regWithException() throws Exception {
        sampleService.regWithException();
    }

    @PostMapping("/regWithAnnotation")
    public void regWithAnnotation() throws Exception {
        sampleService.regWithAnnotation();
    }

    @PostMapping("/registerSample2Two")
    public void registerSample2Two() {
        sample2Service.registerSample2Two();
    }

    @PostMapping("/registerSample2WithException")
    public void registerSample2WithException() throws Exception {
        sample2Service.registerSample2WithException();
    }

    @PostMapping("/regSample2WithException")
    public void regSample2WithException() throws Exception {
        sample2Service.regSample2WithException();
    }

    @PostMapping("/regSample2WithAnnotation")
    public void regSample2WithAnnotation() throws Exception {
        sample2Service.regSample2WithAnnotation();
    }

    @PostMapping("/registerSampleInSample2Transaction")
    public void registerSampleInSample2Transaction() throws Exception {
        sample2Service.registerSampleInSample2Transaction();
    }

}
