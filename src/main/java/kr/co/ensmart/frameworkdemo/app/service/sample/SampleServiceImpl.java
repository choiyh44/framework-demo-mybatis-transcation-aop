package kr.co.ensmart.frameworkdemo.app.service.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ensmart.frameworkdemo.app.dao.sample.SampleMapper;
import kr.co.ensmart.frameworkdemo.app.dao.sample2.Sample2Mapper;
import kr.co.ensmart.frameworkdemo.base.aop.NoAopTx;
import kr.co.ensmart.frameworkdemo.common.dto.sample.Sample;

@Service
public class SampleServiceImpl implements SampleService {
	@Autowired
	private SampleMapper sampleMapper;
	
    @Autowired
    private Sample2Mapper sample2Mapper;

    @NoAopTx
    @Override
	public List<Sample> retrieveAllSamples() {
		return  sampleMapper.selectAllSampleList();
	}

    @Override
    public List<Sample> retrieveSlowSamples() {
        return  sampleMapper.selectSlowSampleList();
    }

    @Override
    public List<Sample> retrieveAllSamplesCaller() {
        return  retrieveAllSamplesTx();
    }

    @NoAopTx
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Sample> retrieveAllSamplesTx() {
        return  sampleMapper.selectAllSampleList();
    }

    @NoAopTx
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void regWithAnnotation() throws Exception {
        Sample sample1 = new Sample();
        sample1.setName("testName1");
        sample1.setDescription("testDescription1");
        sampleMapper.insertSample(sample1);

        if (true) {
            throw new Exception("Tx test exception.");
        }
        
        Sample sample2 = new Sample();
        sample2.setName("testName2");
        sample2.setDescription("testDescription2");

        sampleMapper.insertSample(sample2);
    }
    
}
