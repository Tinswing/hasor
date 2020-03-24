/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.dataway.config;
import net.hasor.core.AppContext;
import net.hasor.dataql.binder.AppContextFinder;
import net.hasor.dataway.daos.ApiQuery;
import net.hasor.dataway.daos.InterfaceReleaseDO;
import net.hasor.utils.ExceptionUtils;
import net.hasor.utils.ResourcesUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.InputStream;

/**
 * Dataway 启动入口
 * @author 赵永春 (zyc@hasor.net)
 * @version : 2020-03-20
 */
@Singleton
public class DatawayFinder extends AppContextFinder {
    @Inject
    private ApiQuery apiQuery;

    @Inject
    public DatawayFinder(AppContext appContext) {
        super(appContext);
    }

    /** 负责处理 <code>import @"/net/hasor/demo.ql" as demo;</code>方式中 ‘/net/hasor/demo.ql’ 资源的加载 */
    public InputStream findResource(String resourceName) {
        InterfaceReleaseDO queryInfo = apiQuery.queryApi(resourceName);
        //        if (queryInfo==null || queryInfo.)
        //
        //
        // .加载资源
        InputStream inputStream = null;
        try {
            inputStream = ResourcesUtils.getResourceAsStream(resourceName);
        } catch (Exception e) {
            throw ExceptionUtils.toRuntimeException(e, throwable -> new RuntimeException("import compiler failed -> '" + resourceName + "' not found.", throwable));
        }
        return inputStream;
    }
}
