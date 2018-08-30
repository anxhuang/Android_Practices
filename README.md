# Android Practices

Language: Java 1.7.0  
Tools: Android Studio 3.1.3 (macOS/Windows)  
Usage: Import as a project with each individual folders  

## Lab01
Layout: layout/*.xml, Linear, RelativeLayout, layout_weight, TextView, ImageView, EditText  

## Lab02
Interactive: Button, onClick method, view.setText(String.valueOf(int))  

## Lab03
CourtCounter: values/style.xml, switch(view.getId())  

## Lab04
CheckBox: CheckBox, StringBuilder, sb.delete(0, sb.length()), Math.max(0,int)  

## Lab05
Fragments: values/dimens, strings.xml, -hXXXdp-port, Fragment, getSupportFragmentManager(), View.OnClickListener  

## Lab07
Activities: RadioButton, ImageButton, Intent, Bundle, onPause(), onResume(), SharedPreferences.Editor(), Log.d(TAG,"comment")  

## Lab08
QuizGame: xmlns:tools="http://schemas.android.com/tools", \<include layout="layout/*.xml">, \<merge>, res/anim/*.xml, overridePendingTransition(in,out), <string-array>, Arrays.fill(), AlertDialog from Lab09  

## Lab09
Dialog: AlertDialog.Builder(), DialogInterface.OnClickListener, custom defined Listener, Items, MultiChoiceItems, SingleChoiceItems, custom DialogFragment with build-in Interface, LayoutInflater, callback  

## Lab10
Thread Animation: values/arrays, .obtainTypedArray(), AnimationDrawable, .start(), .stop(), Handler.postDelayed(Runnable, delayMillis), .removeCallbacks(Runnable), Button.setEnabled(bool)  

## Lab11
ListView: FloatingActionButton, ListView.setEmptyView(view), .setAdapter, BaseAdapter, getCount(), getView(), .setOnItemClickListener(this), custom ArrayList<Model>, Model implements Serializable, .getSerializableExtra(), Adapter.notifyDataSetChanged(), extends custom Activity, list.get(index), list.set(index, item), onSaveInstanceState(), OnStop(), ObjectInput/OutputStream to file with try...catch...finally  

## Lab12
Spinner: Spinner.setAdapter, TypedArray, obtainTypedArray(ResArray), setOnItemSelectedListener(this), .setSelection(index), AlertDialog, builder.setIcon(ResID), dialog.setOnShowListener, editText.addTextChangedListener, custom getModel(), custom RuntimeException, extends custom Fragment, cutomer initializer
