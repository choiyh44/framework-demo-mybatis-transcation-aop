package kr.co.ensmart.frameworkdemo.app.service.sample;

import java.util.List;

import kr.co.ensmart.frameworkdemo.common.dto.sample.Sample;

public interface SampleService {

	public List<Sample> retrieveAllSamples();

	public Sample retrieveSampleById(Integer id);

    /**
     * @return
     */
    public void registerTwo();

    /**
     * 
     */
    public void registerWithException() throws Exception;

    /**
     * 
     */
    public void regWithException() throws Exception;

    /**
     * @throws Exception
     */
    void regWithAnnotation() throws Exception;

    /**
     * @return
     */
    List<Sample> retrieveAllSamplesCaller();

    /**
     * @return
     */
    List<Sample> retrieveAllSamplesTx();

}
