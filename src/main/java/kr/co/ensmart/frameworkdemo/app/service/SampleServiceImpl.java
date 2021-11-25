package kr.co.ensmart.frameworkdemo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ensmart.frameworkdemo.app.dao.sample.SampleMapper;
import kr.co.ensmart.frameworkdemo.common.dto.sample.Sample;

@Service
public class SampleServiceImpl implements SampleService {
	@Autowired
	private SampleMapper sampleMapper;
	
	@Override
	public List<Sample> retrieveAllSamples() {
		return  sampleMapper.selectAllSampleList();
	}

	@Override
	public Sample retrieveSampleById(Integer id) {
		return  sampleMapper.selectSampleById(id);
	}
	
    @Override
    public void registerTwo() {
        Sample sample1 = new Sample();
        sample1.setName("testName1");
        sample1.setDescription("testDescription1");
        sampleMapper.insertSample(sample1);

        Sample sample2 = new Sample();;
        sample2.setName("testName2");
        sample2.setDescription("testDescription2");

        sampleMapper.insertSample(sample2);
    }

    @Override
    public void registerWithException() throws Exception {
        Sample sample1 = new Sample();
        sample1.setName("testName1");
        sample1.setDescription("testDescription1");
        sampleMapper.insertSample(sample1);

        if (true) {
            throw new Exception("Tx test exception.");
        }
        
        Sample sample2 = new Sample();;
        sample2.setName("testName2");
        sample2.setDescription("testDescription2");

        sampleMapper.insertSample(sample2);
    }

    @Override
    public void regWithException() throws Exception {
        Sample sample1 = new Sample();
        sample1.setName("testName1");
        sample1.setDescription("testDescription1");
        sampleMapper.insertSample(sample1);

        if (true) {
            throw new Exception("Tx test exception.");
        }
        
        Sample sample2 = new Sample();;
        sample2.setName("testName2");
        sample2.setDescription("testDescription2");

        sampleMapper.insertSample(sample2);
    }
}
