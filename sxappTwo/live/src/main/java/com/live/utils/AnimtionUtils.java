package com.live.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.live.entity.Constant;

/**
 * Created by Administrator on 2016/12/2.
 */
public class AnimtionUtils {
    private PKFaPaiCompleteListener listener;
    private String TAG = "AnimtionUtils";
    private Animation translateAnimationLeft2;
    private Animation translateAnimationLeft1;
    private Animation translateAnimationLeft3;
    private Animation translateAnimationMillde1;
    private Animation translateAnimationMillde2;
    private Animation translateAnimationMillde3;
    private Animation translateAnimationRight1;
    private Animation translateAnimationRight2;
    private Animation translateAnimationRight3;
    private Animation qmdnAnimLeft1;
    private Animation qmdnAnimMillde1;
    private Animation qmdnAnimRight1;
    private Animation qmdnAnimLeft2;
    private Animation qmdnAnimMillde2;
    private Animation qmdnAnimRight2;
    private Animation qmdnAnimLeft3;
    private Animation qmdnAnimMillde3;
    private Animation qmdnAnimRight3;
    private Animation qmdnAnimLeft4;
    private Animation qmdnAnimMillde4;
    private Animation qmdnAnimRight4;
    private Animation qmdnAnimLeft5;
    private Animation qmdnAnimMillde5;
    private Animation qmdnAnimRight5;
    private static Animation ttdnLeft1;
    private Animation ttdnRight1;
    private Animation ttdnLeft2;
    private Animation ttdnRight2;
    private Animation ttdnLift3;
    private Animation ttdnRight3;
    private Animation ttdnLeft4;
    private Animation ttdnRight4;
    private Animation ttdnLeft5;
    private Animation ttdnRight5;
    private Animation ttdzLeft1;
    private Animation ttdzMiddle1;
    private Animation ttdzRight1;
    private Animation ttdzLeft2;
    private Animation ttdzMiddle2;
    private Animation ttdzRight2;
    private Animation ttdzMiddle3;
    private Animation ttdzMiddle4;
    private Animation ttdzMiddle5;
    private GoldAnimationCompletedListener goldListener;

    public AnimtionUtils(PKFaPaiCompleteListener listener, GoldAnimationCompletedListener goldListener) {
        this.listener = listener;
        this.goldListener = goldListener;
    }

    public static void showGiftAndGameContent(View view, float distance) {
//        ObjectAnimator anim = ObjectAnimator.ofFloat(view.getY(), "translationY", translationX, distance, translationX);
        float v = view.getY() - distance;
        ObjectAnimator showAnimator = ObjectAnimator.ofFloat(view, "translationY", distance, 0);
        Log.d("AnimtionUtils", "SHOW:befroeY:" + view.getY() + ",>>>>afterY:" + v);
        showAnimator.setDuration(500);
        showAnimator.start();
        showAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                Constant.isGiftAndGaemShow = true;
                Log.d("AnimtionUtils", "isGiftAndGaemShow:" + Constant.isGiftAndGaemShow);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    public static void hintGiftAndGameContent(View view, float distance) {
        ObjectAnimator hintAnimator = ObjectAnimator.ofFloat(view, "translationY", 0, distance);
        hintAnimator.setDuration(500);
        hintAnimator.start();
        hintAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                Constant.isGiftAndGaemShow = false;
                Log.d("AnimtionUtils", "isGiftAndGaemShow:" + Constant.isGiftAndGaemShow);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * ObjectAnimator.ofFloat(target,"scaleX",0.45f,1),
     * ObjectAnimator.ofFloat(target,"scaleY",0.45f,1),
     * ObjectAnimator.ofFloat(target,"alpha",0,1)
     */

    public static AnimatorSet getBeginWarringAnimatorSet(View view) {
        AnimatorSet set = new AnimatorSet();
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.45f, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.45f, 1);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        animatorSet.setTarget(view);
        animatorSet.playTogether(scaleX, scaleY, alpha);

        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(view, "scaleX", 1, 0.45f);
        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(view, "scaleY", 1, 0.45f);
        ObjectAnimator alpha2 = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        animatorSet2.setTarget(view);
        animatorSet2.playTogether(scaleX2, scaleY2, alpha2);
        set.setDuration(2000);
        set.playSequentially(animatorSet, animatorSet2);
        return set;
    }


    public void getFaPaiAnimatorSet(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y,
                                    final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y) {

        Log.d("ZhaJinHuaManager", "执行动画fapaiX:" + fapaiX + ",fapaiY:" + fapaiY + ",left1X:" + left1X + ",left1Y:" + left1Y);

        float DISTANCEx = left3X - fapaiX;
        float distanceY = left3Y - fapaiY;
        Log.d("ZhaJinHuaManager", "距离fapaiX:" + DISTANCEx + ",fapaiY:" + distanceY);

        if (translateAnimationLeft1 == null) {
            translateAnimationLeft1 = new TranslateAnimation(0, left1X - fapaiX, 0, left1Y - fapaiY);
        }
        translateAnimationLeft1.setDuration(200);
        view.startAnimation(translateAnimationLeft1);
        translateAnimationLeft1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(1);
                }
//                left2(view, fapaiX, fapaiY, left2X, left2Y, left3X, left3Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, right1X, right1Y, right2X, right2Y, right3X, right3Y);
                millde1(view, fapaiX, fapaiY, left2X, left2Y, left3X, left3Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, right1X, right1Y, right2X, right2Y, right3X, right3Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

        Log.d("translateAnimationLeft2", "translateAnimationLeft2:" + translateAnimationLeft2);


    }

    private void left2(final View view, final float fapaiX, final float fapaiY, final float left2X, final float left2Y, final float left3X, final float left3Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y) {
        if (translateAnimationLeft2 == null) {
            translateAnimationLeft2 = new TranslateAnimation(0, left2X - fapaiX, 0, left2Y - fapaiY);
        }
        translateAnimationLeft2.setDuration(200);
        view.startAnimation(translateAnimationLeft2);

        translateAnimationLeft2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(2);
                }
                millde2(view, fapaiX, fapaiY, left2X, left2Y, left3X, left3Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, right1X, right1Y, right2X, right2Y, right3X, right3Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void left3(final View view, final float fapaiX, final float fapaiY, final float left2X, final float left2Y, final float left3X, final float left3Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y) {

        if (translateAnimationLeft3 == null) {
            translateAnimationLeft3 = new TranslateAnimation(0, left3X - fapaiX, 0, left3Y - fapaiY);
        }
        translateAnimationLeft3.setDuration(200);
        view.startAnimation(translateAnimationLeft3);

        translateAnimationLeft3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(3);
                }
                millde3(view, fapaiX, fapaiY, left2X, left2Y, left3X, left3Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, right1X, right1Y, right2X, right2Y, right3X, right3Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void millde1(final View view, final float fapaiX, final float fapaiY, final float left2X, final float left2Y, final float left3X, final float left3Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y) {

        if (translateAnimationMillde1 == null) {
            translateAnimationMillde1 = new TranslateAnimation(0, millde1X - fapaiX, 0, millde1Y - fapaiY);
        }
        translateAnimationMillde1.setDuration(200);
        view.startAnimation(translateAnimationMillde1);

        translateAnimationMillde1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(4);
                }
                right1(view, fapaiX, fapaiY, left2X, left2Y, left3X, left3Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, right1X, right1Y, right2X, right2Y, right3X, right3Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void millde2(final View view, final float fapaiX, final float fapaiY, final float left2X, final float left2Y, final float left3X, final float left3Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y) {
        if (translateAnimationMillde2 == null) {
            translateAnimationMillde2 = new TranslateAnimation(0, millde2X - fapaiX, 0, millde2Y - fapaiY);
        }
        translateAnimationMillde2.setDuration(200);
        view.startAnimation(translateAnimationMillde2);

        translateAnimationMillde2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(5);
                }
                right2(view, fapaiX, fapaiY, left2X, left2Y, left3X, left3Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, right1X, right1Y, right2X, right2Y, right3X, right3Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void millde3(final View view, final float fapaiX, final float fapaiY, final float left2X, final float left2Y, final float left3X, final float left3Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y) {

        if (translateAnimationMillde3 == null) {
            translateAnimationMillde3 = new TranslateAnimation(0, millde3X - fapaiX, 0, millde3Y - fapaiY);
        }
        translateAnimationMillde3.setDuration(200);
        view.startAnimation(translateAnimationMillde3);

        translateAnimationMillde3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(6);
                }
                right3(view, fapaiX, fapaiY, left2X, left2Y, left3X, left3Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, right1X, right1Y, right2X, right2Y, right3X, right3Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void right1(final View view, final float fapaiX, final float fapaiY, final float left2X, final float left2Y, final float left3X, final float left3Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y) {

        if (translateAnimationRight1 == null) {
            translateAnimationRight1 = new TranslateAnimation(0, right1X - fapaiX, 0, right1Y - fapaiY);
        }
        translateAnimationRight1.setDuration(200);
        view.startAnimation(translateAnimationRight1);

        translateAnimationRight1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(7);
                }
                left2(view, fapaiX, fapaiY, left2X, left2Y, left3X, left3Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, right1X, right1Y, right2X, right2Y, right3X, right3Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void right2(final View view, final float fapaiX, final float fapaiY, final float left2X, final float left2Y, final float left3X, final float left3Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y) {

        if (translateAnimationRight2 == null) {
            translateAnimationRight2 = new TranslateAnimation(0, right2X - fapaiX, 0, right2Y - fapaiY);
        }
        translateAnimationRight2.setDuration(200);
        view.startAnimation(translateAnimationRight2);

        translateAnimationRight2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(8);
                }
                left3(view, fapaiX, fapaiY, left2X, left2Y, left3X, left3Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, right1X, right1Y, right2X, right2Y, right3X, right3Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void right3(View view, float fapaiX, float fapaiY, float left2X, float left2Y, float left3X, float left3Y, float millde1X, float millde1Y, float millde2X, float millde2Y, float millde3X, float millde3Y, float right1X, float right1Y, float right2X, float right2Y, float right3X, float right3Y) {
        if (translateAnimationRight3 == null) {
            translateAnimationRight3 = new TranslateAnimation(0, right3X - fapaiX, 0, right3Y - fapaiY);
        }
        translateAnimationRight3.setDuration(200);
        view.startAnimation(translateAnimationRight3);
        translateAnimationRight3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(9);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


//    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>全民斗牛>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    public void DoQuanMinDouNiuAnimtor(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y,
                                       final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X,
                                       final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {

        Log.d("ZhaJinHuaManager", "执行动画fapaiX:" + fapaiX + ",fapaiY:" + fapaiY + ",left1X:" + left1X + ",left1Y:" + left1Y);

        float DISTANCEx = left3X - fapaiX;
        float distanceY = left3Y - fapaiY;
        Log.d("ZhaJinHuaManager", "距离fapaiX:" + DISTANCEx + ",fapaiY:" + distanceY);

        if (qmdnAnimLeft1 == null) {
            qmdnAnimLeft1 = new TranslateAnimation(0, left1X - fapaiX, 0, left1Y - fapaiY);
        }
        qmdnAnimLeft1.setDuration(200);
        view.startAnimation(qmdnAnimLeft1);
        qmdnAnimLeft1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(1);
                }
                qmdnMiddle1(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X,
                        right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

        Log.d("translateAnimationLeft2", "translateAnimationLeft2:" + translateAnimationLeft2);


    }

    private void qmdnMiddle1(final View view, final float fapaiX, final float fapaiY, final float left1X, float left1Y, final float left2X, final float left2Y,
                             final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y,
                             final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X,
                             final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y,
                             final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimMillde1 == null) {
            qmdnAnimMillde1 = new TranslateAnimation(0, millde1X - fapaiX, 0, millde1Y - fapaiY);
        }
        qmdnAnimMillde1.setDuration(200);
        view.startAnimation(qmdnAnimMillde1);
        qmdnAnimMillde1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(6);
                }
                qmdnRight1(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y, millde2X, millde2Y,
                        millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void qmdnRight1(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1,
                            final float left3X, final float left3Y, final float left4X, final float left4Y,
                            final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y,
                            final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y,
                            final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X,
                            final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimRight1 == null) {
            qmdnAnimRight1 = new TranslateAnimation(0, right1X - fapaiX, 0, right1Y - fapaiY);
        }
        qmdnAnimRight1.setDuration(200);
        view.startAnimation(qmdnAnimRight1);
        qmdnAnimRight1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(11);
                }
                qmdnLeft21(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void qmdnLeft21(final View view, final float fapaiX, final float fapaiY, final float left1X, float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimLeft2 == null) {
            qmdnAnimLeft2 = new TranslateAnimation(0, left2X - fapaiX, 0, left2Y - fapaiY);
        }
        qmdnAnimLeft2.setDuration(200);
        view.startAnimation(qmdnAnimLeft2);
        qmdnAnimLeft2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(2);
                }
                qmdnMiddle2(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void qmdnMiddle2(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X,
                             float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y,
                             final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y,
                             final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimMillde2 == null) {
            qmdnAnimMillde2 = new TranslateAnimation(0, millde2X - fapaiX, 0, millde2Y - fapaiY);
        }
        qmdnAnimMillde2.setDuration(200);
        view.startAnimation(qmdnAnimMillde2);
        qmdnAnimMillde2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(7);
                }
                qmdnRight2(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void qmdnRight2(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimRight2 == null) {
            qmdnAnimRight2 = new TranslateAnimation(0, right2X - fapaiX, 0, right2Y - fapaiY);
        }
        qmdnAnimRight2.setDuration(200);
        view.startAnimation(qmdnAnimRight2);
        qmdnAnimRight2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(12);
                }
                qmdnLeft3(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void qmdnLeft3(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimLeft3 == null) {
            qmdnAnimLeft3 = new TranslateAnimation(0, left3X - fapaiX, 0, left3Y - fapaiY);
        }
        qmdnAnimLeft3.setDuration(200);
        view.startAnimation(qmdnAnimLeft3);
        qmdnAnimLeft3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(3);
                }
                qmdnMillde3(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void qmdnMillde3(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimMillde3 == null) {
            qmdnAnimMillde3 = new TranslateAnimation(0, millde3X - fapaiX, 0, millde3Y - fapaiY);
        }
        qmdnAnimMillde3.setDuration(200);
        view.startAnimation(qmdnAnimMillde3);
        qmdnAnimMillde3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(8);
                }
                qmdnRight3(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });


    }

    private void qmdnRight3(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {

        if (qmdnAnimRight3 == null) {
            qmdnAnimRight3 = new TranslateAnimation(0, right3X - fapaiX, 0, right3Y - fapaiY);
        }
        qmdnAnimRight3.setDuration(200);
        view.startAnimation(qmdnAnimRight3);
        qmdnAnimRight3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(13);
                }
                qmdnLeft4(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void qmdnLeft4(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {

        if (qmdnAnimLeft4 == null) {
            qmdnAnimLeft4 = new TranslateAnimation(0, left4X - fapaiX, 0, left4Y - fapaiY);
        }
        qmdnAnimLeft4.setDuration(200);
        view.startAnimation(qmdnAnimLeft4);
        qmdnAnimLeft4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(4);
                }
                qmdnMiddle4(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void qmdnMiddle4(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimMillde4 == null) {
            qmdnAnimMillde4 = new TranslateAnimation(0, millde4X - fapaiX, 0, millde4Y - fapaiY);
        }
        qmdnAnimMillde4.setDuration(200);
        view.startAnimation(qmdnAnimMillde4);

        qmdnAnimMillde4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(9);
                }
                qmdnRight4(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void qmdnRight4(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimRight4 == null) {
            qmdnAnimRight4 = new TranslateAnimation(0, right4X - fapaiX, 0, right4Y - fapaiY);
        }
        qmdnAnimRight4.setDuration(200);
        view.startAnimation(qmdnAnimRight4);
        qmdnAnimRight4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(14);
                }
                qmdnLeft5(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void qmdnLeft5(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimLeft5 == null) {
            qmdnAnimLeft5 = new TranslateAnimation(0, left5X - fapaiX, 0, left5Y - fapaiY);
        }
        qmdnAnimLeft5.setDuration(200);
        view.startAnimation(qmdnAnimLeft5);
        qmdnAnimLeft5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(5);
                }
                qmdnMiddle5(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void qmdnMiddle5(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (qmdnAnimMillde5 == null) {
            qmdnAnimMillde5 = new TranslateAnimation(0, millde5X - fapaiX, 0, millde5Y - fapaiY);
        }
        qmdnAnimMillde5.setDuration(200);
        view.startAnimation(qmdnAnimMillde5);
        qmdnAnimMillde5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(10);
                }
                qmdnRight5(view, fapaiX, fapaiY, left1X, left2Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, millde1X, millde1Y,
                        millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X, right2Y, right3X,
                        right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void qmdnRight5(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left2Y, final float left2X, float left2Y1, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {

        if (qmdnAnimRight5 == null) {
            qmdnAnimRight5 = new TranslateAnimation(0, right5X - fapaiX, 0, right5Y - fapaiY);
        }
        qmdnAnimRight5.setDuration(200);
        view.startAnimation(qmdnAnimRight5);
        qmdnAnimRight5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(15);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }
//   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>全民斗牛>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void doTTDNAnimator(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y,
                               final float right1X, final float right1Y, final float right2X,
                               final float right2Y, final float right3X, final float right3Y, final float right4X,
                               final float right4Y, final float right5X, final float right5Y) {

        if (ttdnLeft1 == null) {
            ttdnLeft1 = new TranslateAnimation(0, left1X - fapaiX, 0, left1Y - fapaiY);
        }
        ttdnLeft1.setDuration(200);
        view.startAnimation(ttdnLeft1);
        ttdnLeft1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(1);
                }
                ttdnRight1(view, fapaiX, fapaiY, left1X, left1Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, right1X, right1Y, right2X,
                        right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void ttdnRight1(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {


        if (ttdnRight1 == null) {
            ttdnRight1 = new TranslateAnimation(0, right1X - fapaiX, 0, right1Y - fapaiY);
        }
        ttdnRight1.setDuration(200);
        view.startAnimation(ttdnRight1);

        ttdnRight1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(2);
                }
                ttdnLeft2(view, fapaiX, fapaiY, left1X, left1Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, right1X, right1Y, right2X,
                        right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void ttdnLeft2(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (ttdnLeft2 == null) {
            ttdnLeft2 = new TranslateAnimation(0, left2X - fapaiX, 0, left2Y - fapaiY);
        }
        ttdnLeft2.setDuration(200);
        view.startAnimation(ttdnLeft2);
        ttdnLeft2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(3);
                }
                ttdnRight2(view, fapaiX, fapaiY, left1X, left1Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, right1X, right1Y, right2X,
                        right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void ttdnRight2(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (ttdnRight2 == null) {
            ttdnRight2 = new TranslateAnimation(0, right2X - fapaiX, 0, right2Y - fapaiY);
        }
        ttdnRight2.setDuration(200);
        view.startAnimation(ttdnRight2);
        ttdnRight2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(4);
                }
                ttdnLift3(view, fapaiX, fapaiY, left1X, left1Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, right1X, right1Y, right2X,
                        right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void ttdnLift3(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (ttdnLift3 == null) {
            ttdnLift3 = new TranslateAnimation(0, left3X - fapaiX, 0, left3Y - fapaiY);
        }
        ttdnLift3.setDuration(200);
        view.startAnimation(ttdnLift3);
        ttdnLift3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(5);
                }
                ttdnRight3(view, fapaiX, fapaiY, left1X, left1Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, right1X, right1Y, right2X,
                        right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void ttdnRight3(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (ttdnRight3 == null) {
            ttdnRight3 = new TranslateAnimation(0, right3X - fapaiX, 0, right3Y - fapaiY);
        }
        ttdnRight3.setDuration(200);
        view.startAnimation(ttdnRight3);
        ttdnRight3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(6);
                }
                ttdnLeft4(view, fapaiY, fapaiX, left1X, left1Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, right1X, right1Y, right2X,
                        right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void ttdnLeft4(final View view, final float fapaiY, final float fapaiX, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {

        if (ttdnLeft4 == null) {
            ttdnLeft4 = new TranslateAnimation(0, left4X - fapaiX, 0, left4Y - fapaiY);
        }
        ttdnLeft4.setDuration(200);
        view.startAnimation(ttdnLeft4);
        ttdnLeft4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(7);
                }
                ttdnRight4(view, fapaiY, fapaiX, left1X, left1Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, right1X, right1Y, right2X,
                        right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void ttdnRight4(final View view, final float fapaiY, final float fapaiX, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (ttdnRight4 == null) {
            ttdnRight4 = new TranslateAnimation(0, right4X - fapaiX, 0, right4Y - fapaiY);
        }
        ttdnRight4.setDuration(200);
        view.startAnimation(ttdnRight4);
        ttdnRight4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(8);
                }
                ttdnLeft5(view, fapaiY, fapaiX, left1X, left1Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, right1X, right1Y, right2X,
                        right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void ttdnLeft5(final View view, final float fapaiY, final float fapaiX, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (ttdnLeft5 == null) {
            ttdnLeft5 = new TranslateAnimation(0, left5X - fapaiX, 0, left5Y - fapaiY);
        }
        ttdnLeft5.setDuration(200);
        view.startAnimation(ttdnLeft5);
        ttdnLeft5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(9);
                }
                ttdnRight5(view, fapaiY, fapaiX, left1X, left1Y, left2X, left2Y, left3X, left3Y, left4X, left4Y, left5X, left5Y, right1X, right1Y, right2X,
                        right2Y, right3X, right3Y, right4X, right4Y, right5X, right5Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void ttdnRight5(final View view, final float fapaiY, final float fapaiX, final float left1X, final float left1Y, final float left2X, final float left2Y, final float left3X, final float left3Y, final float left4X, final float left4Y, final float left5X, final float left5Y, final float right1X, final float right1Y, final float right2X, final float right2Y, final float right3X, final float right3Y, final float right4X, final float right4Y, final float right5X, final float right5Y) {
        if (ttdnRight5 == null) {
            ttdnRight5 = new TranslateAnimation(0, right5X - fapaiX, 0, right5Y - fapaiY);
        }
        ttdnRight5.setDuration(200);
        view.startAnimation(ttdnRight5);
        ttdnRight5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(10);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }


//    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>天天德州>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void doTTDZAnimator(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y,
                               final float millde1X, final float millde1Y, final float millde2X,
                               final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y,
                               final float right1X, final float right1Y, final float right2X,
                               final float right2Y) {

        if (ttdzLeft1 == null) {
            ttdzLeft1 = new TranslateAnimation(0, left1X - fapaiX, 0, left1Y - fapaiY);
        }
        ttdzLeft1.setDuration(200);
        view.startAnimation(ttdzLeft1);

        ttdzLeft1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(1);
                }
                ttdzMiddle1(view, fapaiY, fapaiX, left1X, left1Y, left2X, left2Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X,
                        right2Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void ttdzMiddle1(final View view, final float fapaiY, final float fapaiX, final float left1X, final float left1Y, final float left2X, final float left2Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y) {
        if (ttdzMiddle1 == null) {
            ttdzMiddle1 = new TranslateAnimation(0, millde1X - fapaiX, 0, millde1Y - fapaiY);
        }
        ttdzMiddle1.setDuration(200);
        view.startAnimation(ttdzMiddle1);
        ttdzMiddle1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(2);
                }
                ttdzRight1(view, fapaiY, fapaiX, left1X, left1Y, left2X, left2Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X,
                        right2Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });


    }

    private void ttdzRight1(final View view, final float fapaiY, final float fapaiX, final float left1X, final float left1Y, final float left2X, final float left2Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y) {
        if (ttdzRight1 == null) {
            ttdzRight1 = new TranslateAnimation(0, right1X - fapaiX, 0, right1Y - fapaiY);
        }
        ttdzRight1.setDuration(200);
        view.startAnimation(ttdzRight1);
        ttdzRight1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(3);
                }
                ttdzLeft2(view, fapaiY, fapaiX, left1X, left1Y, left2X, left2Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X,
                        right2Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void ttdzLeft2(final View view, final float fapaiY, final float fapaiX, final float left1X, final float left1Y, final float left2X, final float left2Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y) {
        if (ttdzLeft2 == null) {
            ttdzLeft2 = new TranslateAnimation(0, left2X - fapaiX, 0, left2Y - fapaiY);
        }
        ttdzLeft2.setDuration(200);
        view.startAnimation(ttdzLeft2);
        ttdzLeft2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(4);
                }
                ttdzMiddle2(view, fapaiY, fapaiX, left1X, left1Y, left2X, left2Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X,
                        right2Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void ttdzMiddle2(final View view, final float fapaiY, final float fapaiX, final float left1X,
                             final float left1Y, final float left2X, final float left2Y, final float millde1X, final float millde1Y,
                             final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X,
                             final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y,
                             final float right2X, final float right2Y) {
        if (ttdzMiddle2 == null) {
            ttdzMiddle2 = new TranslateAnimation(0, millde2X - fapaiX, 0, millde2Y - fapaiY);
        }
        ttdzMiddle2.setDuration(200);
        view.startAnimation(ttdzMiddle2);
        ttdzMiddle2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(5);
                }
                ttdzRight2(view, fapaiX, fapaiY, left1X, left1Y, left2X, left2Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X,
                        right2Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void ttdzRight2(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y) {

        if (ttdzRight2 == null) {
            ttdzRight2 = new TranslateAnimation(0, right2X - fapaiX, 0, right2Y - fapaiY);
        }
        ttdzRight2.setDuration(200);
        view.startAnimation(ttdzRight2);
        ttdzRight2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(6);
                }
                ttdzMiddle3(view, fapaiX, fapaiY, left1X, left1Y, left2X, left2Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X,
                        right2Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void ttdzMiddle3(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y) {
        if (ttdzMiddle3 == null) {
            ttdzMiddle3 = new TranslateAnimation(0, millde3X - fapaiX, 0, millde3Y - fapaiY);
        }
        ttdzMiddle3.setDuration(200);
        view.startAnimation(ttdzMiddle3);

        ttdzMiddle3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(7);
                }
                ttdzMiddle4(view, fapaiX, fapaiY, left1X, left1Y, left2X, left2Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X,
                        right2Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void ttdzMiddle4(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y) {
        if (ttdzMiddle4 == null) {
            ttdzMiddle4 = new TranslateAnimation(0, millde4X - fapaiX, 0, millde4Y - fapaiY);
        }
        ttdzMiddle4.setDuration(200);
        view.startAnimation(ttdzMiddle4);
        ttdzMiddle4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(8);
                }
                ttdzMiddle5(view, fapaiX, fapaiY, left1X, left1Y, left2X, left2Y, millde1X, millde1Y, millde2X, millde2Y, millde3X, millde3Y, millde4X, millde4Y, millde5X, millde5Y, right1X, right1Y, right2X,
                        right2Y);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void ttdzMiddle5(final View view, final float fapaiX, final float fapaiY, final float left1X, final float left1Y, final float left2X, final float left2Y, final float millde1X, final float millde1Y, final float millde2X, final float millde2Y, final float millde3X, final float millde3Y, final float millde4X, final float millde4Y, final float millde5X, final float millde5Y, final float right1X, final float right1Y, final float right2X, final float right2Y) {
        if (ttdzMiddle5 == null) {
            ttdzMiddle5 = new TranslateAnimation(0, millde5X - fapaiX, 0, millde5Y - fapaiY);
        }
        ttdzMiddle5.setDuration(200);
        view.startAnimation(ttdzMiddle5);
        ttdzMiddle5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onPKFaPaiCompleted(9);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }


    public void doGoldAnimation(final View view, float goldX, float goldY, float XiaZhuX, float XiaZhuY) {//,float milldeXiaZhuX,float milldeXiaZhuY,float rightXiaZhuX,float rightXiaZhuY
        Animation goldAnimation = new TranslateAnimation(0, XiaZhuX - goldX, 0, XiaZhuY - goldY);
        goldAnimation.setDuration(500);
        view.startAnimation(goldAnimation);
        goldAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (goldListener != null) {
                    goldListener.GoldAnimationCompleted();
                }
//                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

        });

    }


    public interface PKFaPaiCompleteListener {

        void onPKFaPaiCompleted(int type);
    }

    public interface GoldAnimationCompletedListener {

        void GoldAnimationCompleted();
    }


}
