class Solution {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    var courses = new Course[numCourses];
    for(int i = 0; i < numCourses; i++) {
      courses[i] = new Course(i);
    }
    
    Arrays.stream(prerequisites).forEach(x -> {
      int courseId = x[0];
      int prereqId = x[1];
      
      courses[courseId].addPrereq(courses[prereqId]);
    });
    
    var result = new ArrayList<Integer>();
    for(Course c: courses) {
      //System.out.println("dfs on " + c.id);
      boolean containsCycle = topologicalSort(c, result);
      if (containsCycle) return new int[0];
    }
    
    int[] resultArr = new int[numCourses];
    for(int i=0; i < numCourses; i++) {
      resultArr[i] = result.get(i);
    }
    return resultArr;
  }
  
  boolean topologicalSort(Course c, List<Integer> result){
    if (c.status == Status.SEARCHED) return false;
    
    c.status = Status.SEEN;
    for(Course p: c.prereqs) {
      if (p.status == Status.SEEN) return true;
      boolean containsCycle = topologicalSort(p, result);
      if (containsCycle) return true;
    }
    //System.out.println(c.id);
    result.add(c.id);
    c.status = Status.SEARCHED;
    return false;
  }
}

class Course {
    int id;
    List<Course> prereqs;
    Status status;


    public Course(int id) {
        this.id = id;
        this.prereqs = new ArrayList<>();
        this.status = Status.NOT_SEEN;
    }

    void addPrereq(Course c) {
        this.prereqs.add(c);
    }

    @Override
    public String toString() {
      String prereqsStr = prereqs.stream()
        .map(x -> Integer.toString(x.id))
        .collect(Collectors.joining(", "));
      return "[" + prereqsStr + "]";
    }
}

enum Status {
  NOT_SEEN,
  SEEN,
  SEARCHED;
}
