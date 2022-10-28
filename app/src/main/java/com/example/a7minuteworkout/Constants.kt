package com.example.a7minuteworkout

object Constants {
    fun defaultExcerciseList(): ArrayList<ExcerciseModel> {
        val excerciseList=ArrayList<ExcerciseModel>()
        val jumpingJacks=ExcerciseModel(1,"Jumping Jacks",R.drawable.ic_jumping_jacks,false,false)
        excerciseList.add(jumpingJacks)
        val high_knees_running=ExcerciseModel(2,"High knees running in place ",R.drawable.ic_high_knees_running_in_place,false,false)
        excerciseList.add(high_knees_running)
        val abdominalCrunches=ExcerciseModel(3,"Abdominal crunches",R.drawable.ic_abdominal_crunch,false,false)
        excerciseList.add(abdominalCrunches)
        val lunge=ExcerciseModel(4,"Lunge",R.drawable.ic_lunge,false,false)
        excerciseList.add(lunge)
        val plank=ExcerciseModel(5,"Plank",R.drawable.ic_plank,false,false)
        excerciseList.add(plank)
        val push_up=ExcerciseModel(6,"Push Up",R.drawable.ic_push_up,false,false)
        excerciseList.add(push_up)
        val pushupandrotation=ExcerciseModel(7,"Push up and rotation",R.drawable.ic_push_up_and_rotation,false,false)
        excerciseList.add(pushupandrotation)
        val sidePlank=ExcerciseModel(8,"Side Plank",R.drawable.ic_side_plank,false,false)
        excerciseList.add(sidePlank)
        val squat=ExcerciseModel(9,"Squat",R.drawable.ic_squat,false,false)
        excerciseList.add(squat)
        val stepupontochair=ExcerciseModel(10,"Step up onto chair",R.drawable.ic_step_up_onto_chair,false,false)
        excerciseList.add(stepupontochair)
        val tricepsdip=ExcerciseModel(11,"triceps dip on chair ",R.drawable.ic_triceps_dip_on_chair,false,false)
        excerciseList.add(tricepsdip)
        val wallsit=ExcerciseModel(12,"Wall sit",R.drawable.ic_wall_sit,false,false)
        excerciseList.add(wallsit)
        return excerciseList
    }
}