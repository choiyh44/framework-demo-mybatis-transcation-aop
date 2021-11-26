package kr.co.ensmart.frameworkdemo.app.dao.sample2;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ensmart.frameworkdemo.common.dto.sample.Sample;

@Repository
public interface Sample2Mapper {
	List<Sample> selectAllSampleList();
	
	Sample selectSampleById(Integer id);

    /**
     * @param sample1
     */
    void insertSample(Sample sample1);

}
