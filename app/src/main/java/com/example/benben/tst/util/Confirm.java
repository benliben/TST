//package com.example.benben.tst.util;
//
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.view.View;
//import android.view.WindowManager;
//
//
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.afollestad.materialdialogs.Theme;
//import com.example.benben.tst.App;
//import com.example.benben.tst.R;
//import com.example.benben.tst.http.EventFinish;
//
//import java.util.ArrayList;
//
///**
// * 系统所有对话框的工具类
// */
//public class Confirm {
//
//    public static void Show(Context c,String title, String ds, final EventFinish event) {
//        new MaterialDialog.Builder(c).theme(Theme.LIGHT).iconRes(R.drawable.ic_launcher)
//                .limitIconToDefaultSize().backgroundColorRes(R.color.dialog_bg) // limits the displayed icon size to 48dp
//                .title(title).titleColorRes(R.color.dialog_title).titleDividerColorRes(R.color.dialog_line)
//                .content(ds).contentColorRes(R.color.dialog_message)
//                .positiveText(R.string.dialog_ok).positiveColorRes(R.color.dialog_positive)
//                .dismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        if (event != null)
//                            event.onFinish(1);
//                    }
//                })
//                .show();
//	}
//    public static void ShowTwo(Context c,String title, String ds, final EventFinish event) {
//        new MaterialDialog.Builder(c).theme(Theme.LIGHT).iconRes(R.drawable.ic_launcher)
//                .limitIconToDefaultSize().backgroundColorRes(R.color.dialog_bg) // limits the displayed icon size to 48dp
//                .title(title).titleColorRes(R.color.dialog_title).titleDividerColorRes(R.color.dialog_line)
//                .content(ds).contentColorRes(R.color.dialog_message)
//                .negativeText(R.string.dialog_cancel).negativeColorRes(R.color.dialog_negative)
//                .positiveText(R.string.dialog_ok).positiveColorRes(R.color.dialog_positive).callback(new MaterialDialog.ButtonCallback() {
//            @Override
//            public void onPositive(MaterialDialog dialog) {
//                if (event != null)
//                    event.onFinish(1);
//            }
//
//            @Override
//            public void onNegative(MaterialDialog dialog) {
//                if (event != null)
//                    event.onFinish(null);
//            }
//        }).cancelable(false)
//                .show();
//
//    }
//
//    public static void ShowTwo1(Context c,String title, String ds, final EventFinish event) {
//        new MaterialDialog.Builder(c).theme(Theme.LIGHT)
//                .limitIconToDefaultSize().backgroundColorRes(R.color.dialog_bg) // limits the displayed icon size to 48dp
//                .title(title).titleColorRes(R.color.dialog_title).titleDividerColorRes(R.color.dialog_line)
//                .content(ds).contentColorRes(R.color.dialog_message)
//                .negativeText(R.string.dialog_cancel).negativeColorRes(R.color.dialog_negative)
//                .positiveText("情况属实").positiveColorRes(R.color.dialog_positive).callback(new MaterialDialog.ButtonCallback() {
//            @Override
//            public void onPositive(MaterialDialog dialog) {
//                if (event != null)
//                    event.onFinish(1);
//            }
//
//            @Override
//            public void onNegative(MaterialDialog dialog) {
//                if (event != null)
//                    event.onFinish(null);
//            }
//        }).cancelable(false)
//                .show();
//
//    }
//
//    public static void showSystemDialog(String title,String msg,String button,final EventFinish event) {
//
////        AlertDialog.Builder builder = null;
////        if (Build.VERSION.SDK_INT>11) {
////            builder = new AlertDialog.Builder(App.getInstance().getApplicationContext(), AlertDialog.THEME_HOLO_LIGHT);
////        } else {
////            builder = new AlertDialog.Builder(App.getInstance().getApplicationContext());
////        }
////        builder.setTitle(title);
////        builder.setMessage(msg)
////                .setNegativeButton(button, new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        if (event==null){
////                            dialog.dismiss();
////                        } else {
////                            event.onFinish(dialog);
////                        }
////                    }
////                });
////        builder.setCancelable(false);
////        AlertDialog ad = builder.create();
////        ad.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG); //系统中关机对话框就是这个属性
////        ad.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
////        ad.setCanceledOnTouchOutside(false);                                   //点击外面区域不会让dialog消失
////        ad.show();
//
//        MaterialDialog ad = new MaterialDialog.Builder(App.getInstance().getApplicationContext()).theme(Theme.LIGHT).iconRes(R.drawable.ic_launcher)
//                .limitIconToDefaultSize().backgroundColorRes(R.color.dialog_bg) // limits the displayed icon size to 48dp
//                .title(title).titleColorRes(R.color.dialog_title).titleDividerColorRes(R.color.dialog_line)
//                .content(msg).contentColorRes(R.color.dialog_message)
//                .positiveText(button).positiveColorRes(R.color.dialog_positive)
//
//                .dismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        if (event != null)
//                            event.onFinish(1);
//                    }
//                }).build();
//        ad.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG); //系统中关机对话框就是这个属性
//        ad.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        ad.show();
//    }
//
//    public static void ShowList(Context c,String title,String[] items,final MaterialDialog.ListCallback event) {
//        new MaterialDialog.Builder(c).theme(Theme.LIGHT).backgroundColorRes(R.color.dialog_bg)
//                .title(title).titleColorRes(R.color.dialog_title).titleDividerColorRes(R.color.dialog_line)
//                .items(items).contentColorRes(R.color.dialog_message).listDividerColorRes(R.color.dialog_divider)
//                .itemsCallback(event)
//                .show();
//    }
//    public static void ShowList(Context c,String[] items,final MaterialDialog.ListCallback event) {
//        ShowList(c,"菜单",items,event);
//    }
//
//    public static void ShowList(Context c,String title, final ArrayList<? extends InputParams> paramses, final EventFinish event) {
//
//        String[] items = new String[paramses.size()];
//        int i = 0,select = -1;
//
//        for (InputParams ip:paramses) {
//            if (ip.isSelected()) {
//                select = i;
//            }
//            items[i++] = ip.toString();
//        }
//        new MaterialDialog.Builder(c).theme(Theme.LIGHT).backgroundColorRes(R.color.dialog_bg).searchable(true)
//                .title(title).titleColorRes(R.color.dialog_title).titleDividerColorRes(R.color.dialog_line)
//                .items(items).contentColorRes(R.color.dialog_message).listDividerColorRes(R.color.dialog_divider)
//                .itemsCallbackSingleChoice(select, new MaterialDialog.ListCallbackSingleChoice() {
//                    @Override
//                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
//                        for (int i=0;i<paramses.size();i++) {
//                            if (i==which) {
//                                paramses.get(i).setSelected(true);
//                            } else {
//                                paramses.get(i).setSelected(false);
//                            }
//                        }
//                        if (event!=null) {
//                            event.onFinish(paramses.get(which));
//                        }
//                        return true; // allow selection
//                    }
//                })
//                .show();
//    }
//
//    public static void ShowMultList(Context c,String title,final ArrayList<InputParams> paramses, final EventFinish event) {
//        String[] items = new String[paramses.size()];
//        int i = 0;
//        ArrayList<Integer> array = new ArrayList<Integer>();
//        for (InputParams ip:paramses) {
//            if (ip.isSelected()) {
//                array.add(i);
//            }
//            items[i++] = ip.toString();
//        }
//        final Integer[] choices = new Integer[array.size()];
//        array.toArray(choices);
//        new MaterialDialog.Builder(c).theme(Theme.LIGHT).backgroundColorRes(R.color.dialog_bg)
//                .title(title).titleColorRes(R.color.dialog_title).titleDividerColorRes(R.color.dialog_line)
//                .items(items).contentColorRes(R.color.dialog_message).listDividerColorRes(R.color.dialog_divider)
//                .itemsCallbackMultiChoice(choices, new MaterialDialog.ListCallbackMultiChoice() {
//                    @Override
//                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
//
//                        if (which.length > 0) {
//                            ArrayList<Integer> list = new ArrayList<Integer>();
//                            int index = which[which.length - 1];
//                            if (paramses.get(index).isAll()) {
//                                for (InputParams ip:paramses) {
//                                    if (ip.isAll()) {
//                                        list.add(index);
//                                        ip.setSelected(true);
//                                    } else{
//                                        ip.setSelected(false);
//                                    }
//                                }
//                            } else {
//                                for (int i = 0; i < paramses.size(); i++) {
//                                    boolean select = false;
//                                    for (Integer in : which) {
//                                        if (in == i) {
//                                            select = true;
//                                            break;
//                                        }
//                                    }
//                                    if (select) {
//                                        if (paramses.get(i).isAll()) {
//                                            paramses.get(i).setSelected(false);
//                                        } else {
//                                            paramses.get(i).setSelected(true);
//                                            list.add(i);
//                                        }
//                                    } else {
//                                        paramses.get(i).setSelected(false);
//                                    }
//                                }
//                            }
//
//                            Integer[] selec = new Integer[list.size()];
//                            which = list.toArray(selec);
//
//                        } else {
//                            for (InputParams ip:paramses) {
//                                ip.setSelected(false);
//                            }
//                            which = new Integer[0];
//                        }
//                        dialog.setSelectedIndices(which);
//                        return true;
//                    }
//                }).alwaysCallMultiChoiceCallback().positiveText("确定").positiveColorRes(R.color.dialog_positive)
//                .negativeText("取消").negativeColorRes(R.color.dialog_negative)
//                .callback(new MaterialDialog.ButtonCallback() {
//                    @Override
//                    public void onPositive(MaterialDialog dialog) {
//                        super.onPositive(dialog);
//                        if (event != null)
//                            event.onFinish(paramses);
//                        Logs.e("onPositive");
//                    }
//                }).cancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                for (int i=0;i<paramses.size();i++) {
//                    paramses.get(i).setSelected(false);
//                    for (Integer in:choices) {
//                        if (i == in) {
//                            paramses.get(i).setSelected(true);
//                            break;
//                        }
//                    }
//                }
//            }
//        }).bottomDividerColorRes(R.color.dialog_line)
//                .show();
//    }
//    public static MaterialDialog ShowList(Context c,MaterialDialog.MoreCallback moreCallback,String title, ArrayList<? extends InputParams> paramses, final EventFinish event) {
//        String[] items = null;
//        int i = 0, select = -1;
//        if (paramses!=null) {
//            items = new String[paramses.size()];
//            for (InputParams ip : paramses) {
//                if (ip.isSelected()) {
//                    select = i;
//                }
//                items[i++] = ip.toString();
//            }
//        } else {
//            items = new String[0];
//        }
//        MaterialDialog dialog = new MaterialDialog.Builder(c).theme(Theme.LIGHT).backgroundColorRes(R.color.dialog_bg).searchable(true)
//                .moreable(true).moreCallback(moreCallback)
//                .title(title).titleColorRes(R.color.dialog_title).titleDividerColorRes(R.color.dialog_line)
//                .items(items).contentColorRes(R.color.dialog_message).listDividerColorRes(R.color.dialog_divider)
//                .itemsCallbackSingleChoice(select, new MaterialDialog.ListCallbackSingleChoice() {
//                    @Override
//                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
//                        Object obj = dialog.getData();
//                        if (obj instanceof ArrayList) {
//                            ArrayList<InputParams> paramses = (ArrayList<InputParams>) obj;
//                            for (int i = 0; i < paramses.size(); i++) {
//                                if (i == which) {
//                                    paramses.get(i).setSelected(true);
//                                } else {
//                                    paramses.get(i).setSelected(false);
//                                }
//                            }
//                            if (event != null) {
//                                event.onFinish(paramses.get(which));
//                            }
//                        }
//                        return true; // allow selection
//                    }
//                }).build();
//        dialog.setData(paramses);
//        return dialog;
//    }
//}
