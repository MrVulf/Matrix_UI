# Matrix_UI
 
Matrix structure:
    IVECTOR(1): {v11, v12, ... , v1n}
    IVECTOR(2): {v21, v22, ...., v2n}
    ...
    IVECTOR(m): {vm1, vm2, ... , vmn}

The strategy of filling in the matrix:
    Stage 1: Fill in as a sparse matrix with sparse vectors.
        You must fill in every odd element in each vector
        Note: control the number of filled cells
        Note: If a matrix has only SpareVectors -> it is SpareMatrix
        Examples:
            |0|1|2|3|       |0|1|2|3|4|
            {0 ? 0 ?}       {0 ? 0 ? 0}  ---> this vectors are the SparseVectors for the build strategy
            {0 ? 0 ?}       {0 ? 0 ? 0}
            {0 ? 0 ?}       {0 ? 0 ? 0}
            {0 ? 0 ?}       {0 ? 0 ? 0}


    Stage 2: Fill in the remaining elements (not zero):
        Fill in the remaining cells until the number of allowed cells != 0
        Note: if the user tries to fill in the SpareMatrix with too many non-zero elements, an exception will appear.
        Examples:
            |0|1|2|3|       |0|1|2|3|4|
            {* ? * ?}       {* ? * ? *} ---> this vectors are the NormalVectors for the build strategy
            {* ? * ?}       {* ? * ? *}
            {* ? 0 ?}       {* ? * ? *}
            {0 ? 0 ?}       {* ? 0 ? 0}

The matrix is rendered in HomeController:
To do this, the button handlers have an algorithm:
    1) Create a matrix (of the correct type)
    2) Initializing the matrix using the InitiatorMatrix
    3) Putting the matrix in the decorator
    4) Drawing the matrix:
        a) Checking input conditions from the UI
        b) Calling the draw method for the matrix (passing the IMatrixDrawer object)
        c) Delegating the matrix display to the IMatrixDrawer object
        d) Executing the redefined drawing method

The project has the ability do "Undo" command - removing the last action:
The mechanism's structure:
    1) ICommand interface
    2) AbstractCommand class
    3) MyCommand - the HomeController's internal class
    4) CommandManager - the singleton entity for collection action
How it use:
    1) An user should made his class and extends it from AbstractCommand
    2) The user's class overrides the "doExecute" method.
    3) The user makes instance and call method "execute".
How it work:
    1) CommandManager collects commands:
        AbstractCommand provides final method "execute" ->
        Inside, it takes CommandManager state and registers the command if it possible.
    2) When an user calls CommandManager.undo():
        The manager removes the last command and
            repeats all actions from the list starting from the first one