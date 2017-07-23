/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sb.app.mvp.presenters.base;


/**
 * 文件名称：{@link Presenter}
 * <br/>
 * 功能描述： Interface representing a Presenter in a model view presenter (MVP) pattern.
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/5 12:38
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/5 12:38
 * <br/>
 * 修改备注：
 */
public interface Presenter<T> {
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();

    /**
     * 连接试图
     *
     * @param v 视图
     */
    void attachView(T v);


}
