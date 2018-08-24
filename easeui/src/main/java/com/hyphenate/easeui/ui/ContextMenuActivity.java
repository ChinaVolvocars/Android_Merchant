/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hyphenate.easeui.ui;

import android.view.MotionEvent;
import android.view.View;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.easeui.R;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;

public class ContextMenuActivity extends BaseMvpActivity {
    public static final int RESULT_CODE_COPY = 1;
    public static final int RESULT_CODE_DELETE = 2;
    public static final int RESULT_CODE_FORWARD = 3;
	int type;
	EMMessage message;

	@Override
	protected BasePresenter createPresenterInstance() {
		EMMessage message = getIntent().getParcelableExtra("message");
		type = message.getType().ordinal();
		return null;
	}

	@Override
	protected int getLayoutId() {
		if (type == EMMessage.Type.TXT.ordinal()) {
			if(message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_BIG_EXPRESSION, false)){
				return R.layout.em_context_menu_for_image;
			} else{
				return R.layout.em_context_menu_for_text;
			}
		} else if (type == EMMessage.Type.IMAGE.ordinal()) {
			return R.layout.em_context_menu_for_image;
		} else if (type == EMMessage.Type.VOICE.ordinal()) {
			return R.layout.em_context_menu_for_voice;
		}
		return 0;
	}

	@Override
	protected void onViewCreated() {
	}

	@Override
	protected void doLogicFunc() {
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	public void copy(View view){
		setResult(RESULT_CODE_COPY);
		finish();
	}
	public void delete(View view){
		setResult(RESULT_CODE_DELETE);
		finish();
	}

	public void forward(View view){
		setResult(RESULT_CODE_FORWARD);
		finish();
	}
}