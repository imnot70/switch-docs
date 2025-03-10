package org.yjsq.wk.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.yjsq.wk.mapper.CategoryMapper;

@Component
public class LoadDataRunner implements ApplicationRunner {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO category级联结构放到cache里面

    }
}
