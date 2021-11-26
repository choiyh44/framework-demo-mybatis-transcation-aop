package kr.co.ensmart.frameworkdemo.app.service.sample2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ensmart.frameworkdemo.app.dao.sample.SampleMapper;
import kr.co.ensmart.frameworkdemo.app.dao.sample2.Sample2Mapper;
import kr.co.ensmart.frameworkdemo.common.dto.sample.Sample;

@Service
public class Sample2ServiceImpl implements Sample2Service {
    @Autowired
    private Sample2Mapper sample2Mapper;

    @Autowired
    private SampleMapper sampleMapper;

    @Override
    public void registerSample2Two() {
        Sample sample1 = new Sample();
        sample1.setName("testName1");
        sample1.setDescription("testDescription1");
        sample2Mapper.insertSample(sample1);

        Sample sample2 = new Sample();;
        sample2.setName("testName2");
        sample2.setDescription("testDescription2");

        sample2Mapper.insertSample(sample2);
    }

    @Override
    public void registerSample2WithException() throws Exception {
        Sample sample1 = new Sample();
        sample1.setName("testName1");
        sample1.setDescription("testDescription1");
        sample2Mapper.insertSample(sample1);

        if (true) {
            throw new Exception("Tx test exception.");
        }
        
        Sample sample2 = new Sample();;
        sample2.setName("testName2");
        sample2.setDescription("testDescription2");

        sample2Mapper.insertSample(sample2);
    }

    @Override
    public void regSample2WithException() throws Exception {
        Sample sample1 = new Sample();
        sample1.setName("testName1");
        sample1.setDescription("testDescription1");
        sample2Mapper.insertSample(sample1);

        if (true) {
            throw new Exception("Tx test exception.");
        }
        
        Sample sample2 = new Sample();;
        sample2.setName("testName2");
        sample2.setDescription("testDescription2");

        sample2Mapper.insertSample(sample2);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager="displayRwdbTxManager")
    public void regSample2WithAnnotation() throws Exception {
        Sample sample1 = new Sample();
        sample1.setName("testName1");
        sample1.setDescription("testDescription1");
        sample2Mapper.insertSample(sample1);

        if (true) {
            throw new Exception("Tx test exception.");
        }
        
        Sample sample2 = new Sample();
        sample2.setName("testName2");
        sample2.setDescription("testDescription2");

        sample2Mapper.insertSample(sample2);
    }

    @Override
    public void registerSampleInSample2Transaction() throws Exception {
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
