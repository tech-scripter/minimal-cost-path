public class MinCostPath {

    /**
     * Временная сложность O(MxN)
     * @param costs - массив стоимостей
     * @return - минимальные затраты на перемещение из одной клетки в другую
     */
    public static int findMinCost(int[][] costs) {

        // Базовый случай
        if (costs == null || costs.length == 0) {
            return 0;
        }

        int row = costs.length;
        int column = costs[0].length;


        // Массив с расстояниями от начальной клетки до всех остальных клеток
        int[][] grid = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // заполняем массив снизу вверх, к примеру от (0, 0) до (4, 4)
                grid[i][j] = costs[i][j];

                // заполняем первый ряд массива расстояниями от клетки (0,0) до текущей
                if (i == 0 && j > 0) {
                    grid[0][j] += grid[0][j - 1];
                }

                // заполняем первую колонку массива расстояниями от клетки (0,0) до текущей
                else if (j == 0 && i > 0) {
                    grid[i][0] += grid[i - 1][0];
                }

                // заполняем каждый ряд, кроме первого и первой колонки, расстояниями от клетки (0,0) до текущей
                else if (i > 0) {
                    grid[i][j] += Integer.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }

        // Массив с расстояниями от начальной клетки (0,0) до всех остальных клеток заполнен.
        // Находим разность значения клетки (0,0) и последней клетки массива (grid.length - 1, grid.length - 1)
        return grid[row - 1][column - 1] - costs[0][0];
    }
}