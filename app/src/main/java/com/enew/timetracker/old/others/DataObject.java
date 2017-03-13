/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.enew.timetracker.old.others;

public class DataObject {
    private String mText1;
    private String mText2;
    private String mText3;

    DataObject(String text1, String text2, String text3) {
        mText1 = text1;
        mText2 = text2;
        mText3 = text3;
    }

    public String getmText1() {

        return mText1;
    }

    public void setmText1(String mText1) {

        this.mText1 = mText1;
    }

    public String getmText2() {

        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public String getmText3() {

        return mText3;
    }

    public void setmText3(String mText3) {

        this.mText3 = mText3;
    }


}
